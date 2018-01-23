package com.koubei.handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koubei.handler.model.m_InvokeConfig;
import com.koubei.util.com_HashTable;

import java.io.*;
import java.nio.charset.Charset;

public class ctrl_InvokeConfig {
    public static m_InvokeConfig GetConfig(String key)
            throws FileNotFoundException {
        String cacheKey = "m_InvokeConfig_" + key;
        m_InvokeConfig invokeConfig = (m_InvokeConfig) com_HashTable
                .get(cacheKey);
        if (invokeConfig == null) {
            String path = ctrl_InvokeConfig.class.getResource("/GetCase.json").getFile();
            JsonParser parse = new JsonParser();

            Reader reader=new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8"));
            JsonObject json = (JsonObject) parse.parse(reader);
            JsonArray array = json.get("InvokeConfig").getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonObject subObject = array.get(i).getAsJsonObject();
                String id = subObject.get("ID").getAsString();
                String className = subObject.get("ClassName").getAsString();
                String methodName = subObject.get("MethodName").getAsString();
                if (id.equals(key)) {
                    invokeConfig = new m_InvokeConfig();
                    invokeConfig.setId(id);
                    invokeConfig.setClassName(className);
                    invokeConfig.setMethodName(methodName);
                    break;
                }
            }
            com_HashTable.put(cacheKey, invokeConfig);
        }
        return invokeConfig;
    }
}
