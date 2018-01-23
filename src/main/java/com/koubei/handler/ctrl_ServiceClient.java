package com.koubei.handler;

import com.google.gson.Gson;
import com.koubei.cache.srv_CacheManager;
import com.koubei.handler.model.m_InvokeConfig;
import com.koubei.util.com_TypeHelper;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ctrl_ServiceClient {
    /**
     * 获取数据
     *
     * @param isCache
     * @param invokeKey
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> GetService(boolean isCache, String invokeKey,
                                         Object[] params,Class<? extends T> clazz) throws Exception {
        if (invokeKey == null || invokeKey.length() == 0) {
            return null;
        }
        m_InvokeConfig mic = ctrl_InvokeConfig.GetConfig(invokeKey);
        if (mic == null) {
            return null;
        }
        // 反射方法
        Class<?> type = Class.forName(mic.getClassName());
        Class<?>[] parameterTypes = null;
        if (params != null && params.length > 0) {
            parameterTypes = new Class<?>[params.length];
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    parameterTypes[i] = params[i].getClass();
                }
            }
        }
        Method method = type.getMethod(mic.getMethodName(), parameterTypes);
        Object obj = type.newInstance();
        // id字符串
        Object seed = method.invoke(obj, params);
        if (seed == null) {
            return null;
        }
        // 缓存数据
        Object data = srv_CacheManager.cacheData(isCache, method, seed,clazz);
        if (data == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        if (com_TypeHelper.isCollection(data)) {
            Collection<? extends T> array = (Collection<? extends T>) data;

            for (T t : array) {
                list.add(t);
            }
            return list;
        } else {
            list.add((T) data);
            return list;
        }
    }
}
