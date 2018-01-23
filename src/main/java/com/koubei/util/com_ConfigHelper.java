package com.koubei.util;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import java.util.HashMap;
import java.util.Map;

public class com_ConfigHelper {
    public static <T> T getConfig(String filePath, Class<T> type) {
        return null;
    }

    public static Map<String, String> getConfigStrings(String filePath, String[] keys)
            throws ConfigurationException {

        PropertiesConfiguration config = getPropertiesConfiguration(filePath);

        Map<String, String> map = new HashMap<String, String>();
        for (String s : keys) {
            String value = config.getString(s);
            map.put(s, value);
        }
        return map;
    }

    public static String getConfigString(String filePath, String key) throws ConfigurationException {
        PropertiesConfiguration config = getPropertiesConfiguration(filePath);
        String value = config.getString(key);
        return value;
    }

    private static PropertiesConfiguration getPropertiesConfiguration(
            String filePath) throws ConfigurationException {
        Parameters params = new Parameters();
        PropertiesBuilderParameters bp = params.properties().setFileName(
                filePath);

        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
                PropertiesConfiguration.class).configure(bp);
        PropertiesConfiguration config = builder.getConfiguration();
        return config;
    }
}
