package com.koubei.cache;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.koubei.annotation.Cache;
import com.koubei.cache.model.m_CacheConfig;
import com.koubei.util.com_MemcachedHelper;
import com.koubei.util.com_StringHelper;
import com.koubei.util.com_TypeHelper;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class srv_CacheManager {
    /**
     * @param @param  isCache
     * @param @param  method
     * @param @param  seed
     * @param @return
     * @param @throws Exception 设定文件
     * @return Object 返回类型
     * @throws
     * @Title: cacheData
     * @Description: TODO(缓存数据)
     */
    public static Object cacheData(boolean isCache, Method method, Object seed,Class clazz)
            throws Exception {
        if (method == null || seed == null) {
            return null;
        }
        String[] ids = formatCacheKeySeed(seed);
        return buildCache(isCache, method, ids,clazz);
    }

    private static String[] formatCacheKeySeed(Object seed) {
        // 判断是否是集合
        boolean b = com_TypeHelper.isCollection(seed);
        if (b) {
            Collection<?> array = (Collection<?>) seed;
            List<String> list = new ArrayList<String>();
            for (Object item : array) {
                list.add(item.toString());
            }
            String[] strings = new String[array.size()];
            return list.toArray(strings);
        } else {
            return new String[]{seed.toString()};
        }
    }

    private static Object buildCache(boolean isCache, Method method,
                                     String[] keys,Class clazz) throws Exception {
        Cache cache = method.getAnnotation(Cache.class);
        if (cache == null) {
            return null;
        }
        m_CacheConfig cacheConfig = srv_CacheConfig.GetConfig(cache.value());
        String pre = cacheConfig.getPre();
        Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        if (isCache) {
            Map<String, String> mapKeys = new LinkedHashMap<String, String>();
            for (String s : keys) {
                String cacheKey = pre.concat(s);
                mapKeys.put(cacheKey, s);
            }
            String[] strings = new String[mapKeys.size()];
            String[] cacheKeys = mapKeys.keySet().toArray(strings);
            Map<String, Object> cacheMap = com_MemcachedHelper
                    .getMulti(cacheKeys);
            if (cacheMap == null || cacheMap.size() == 0) {
                // 从数据库中获取
                // return null;
            }
            List<String> cacheObjKeys = new ArrayList<String>();
            for (String item : mapKeys.keySet()) {
                boolean mapKey = cacheMap.containsKey(item);
                if (!mapKey) {
                    cacheObjKeys.add(item);
                }
            }
            List<Object> cacheObj = new ArrayList<Object>();
            if (cacheMap.size() > 0 && cacheObjKeys.size() == 0) {
                // 添加到缓存里面
                for (String item : cacheKeys) {
                    Object cacheValue = cacheMap.get(item);
                    if (cacheValue == null) {
                        continue;
                    }
                    Object o=gson.fromJson(cacheValue.toString(), clazz);
                    cacheObj.add(o);
                }
                return cacheObj;
            } else {
                // 从数据库中获取数据
                Object obj = invokeData(cacheConfig, keys);
                if (obj != null) {
                    // 判断是否是集合
                    if (com_TypeHelper.isCollection(obj)) {
                        // 添加到memached中
                        Collection<?> array = (Collection<?>) obj;
                        for (Object item : array) {
                            String cacheKey = pre.concat(srv_CacheKey
                                    .toPrimaryKey(item));
                            String json=gson.toJson(item);
                            com_MemcachedHelper.add(cacheKey, json,
                                    cacheConfig.getExpTime());
                            cacheMap.put(cacheKey, item);
                        }
                    } else {
                        String cacheKey = pre.concat(srv_CacheKey
                                .toPrimaryKey(obj));
                        String json=gson.toJson(obj);
                        com_MemcachedHelper.add(cacheKey, json,
                                cacheConfig.getExpTime());
                        cacheMap.put(cacheKey, obj);
                    }
                }
                for (String item : cacheKeys) {
                    cacheObj.add(cacheMap.get(item));
                }
                return cacheObj;
            }
        } else {
            // 从数据库中获取数据
            Object obj = invokeData(cacheConfig, keys);
            if (obj == null) {
                return null;
            }
            boolean b = com_TypeHelper.isCollection(obj);
            if (b) {
                Collection<?> array = (Collection<?>) obj;
                Map<String, Object> map = new LinkedHashMap<String, Object>(
                        array.size());
                List<Object> list = new LinkedList<Object>();
                for (Object o : array) {
                    map.put(srv_CacheKey.toPrimaryKey(o), o);
                }
                for (String key : keys) {
                    if (!map.isEmpty() && map.containsKey(key)
                            && map.get(key) != null) {
                        list.add(map.get(key));
                    }
                }
                return list;
            } else {
                return obj;
            }
        }
    }

    /**
     * @param @param  cacheConfig
     * @param @param  keys
     * @param @return
     * @param @throws Exception 设定文件
     * @return Object 返回类型
     * @throws
     * @Title: invokeData
     * @Description: TODO(反射从数据库中获取数据)
     */
    private static Object invokeData(m_CacheConfig cacheConfig, String[] keys)
            throws Exception {
        String invokePrams = com_StringHelper.join(keys, ",");
        Class<?> type = Class.forName(cacheConfig.getClassName());
        String paramType=cacheConfig.getParamType();
        Class<?> ptype=Class.forName(paramType);
        Method method = type.getMethod(cacheConfig.getMethodName(),
                ptype);
        Object p=ConvertUtils.convert(invokePrams,ptype);
        Object obj = type.newInstance();
        return method.invoke(obj, p);
    }
}
