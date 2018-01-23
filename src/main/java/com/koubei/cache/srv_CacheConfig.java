package com.koubei.cache;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koubei.cache.model.m_CacheConfig;
import com.koubei.util.com_HashTable;

import java.io.*;
import java.nio.charset.Charset;

public class srv_CacheConfig {
    public static m_CacheConfig GetConfig(String key) throws FileNotFoundException {
        String cacheKey = "m_CacheConfig_" + key;
        m_CacheConfig cacheConfig = (m_CacheConfig) com_HashTable
                .get(cacheKey);
        if (cacheConfig == null) {
            JsonParser parse = new JsonParser();
            String path = srv_CacheConfig.class.getResource("/CacheCase.json").getFile();
            Reader reader = new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8"));
            JsonObject json = (JsonObject) parse.parse(reader);
            JsonArray array = json.get("CacheConfig").getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonObject subObject = array.get(i).getAsJsonObject();
                String id = subObject.get("ID").getAsString();
                String className = subObject.get("ClassName").getAsString();
                String methodName = subObject.get("MethodName").getAsString();
                int expTime = subObject.get("ExpTime").getAsInt();
                String pre = subObject.get("Pre").getAsString();
                String paramType = subObject.get("ParamType").getAsString();
                if (id.equals(key)) {
                    cacheConfig = new m_CacheConfig();
                    cacheConfig.setId(id);
                    cacheConfig.setClassName(className);
                    cacheConfig.setMethodName(methodName);
                    cacheConfig.setExpTime(expTime);
                    cacheConfig.setPre(pre);
                    cacheConfig.setParamType(paramType);
                    break;
                }
            }
            com_HashTable.put(cacheKey, cacheConfig);
        }
        return cacheConfig;
    }
}
