package com.moda.utils;

import com.moda.core.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {

    private ConfigFileReader(){
        throw new IllegalStateException("This is utility class");
    }

    public static String getConfigPropertyValue(String propertyName){

        String configPath = "";
        try(InputStream inputStream = new FileInputStream(Constants.CONFIG_PATH)) {

            Properties properties = new Properties();
            properties.load(inputStream);
            configPath = properties.getProperty(propertyName);

        } catch (IOException e) {
            LogHelper.getLogger().info(e.getMessage());
        }

        return configPath;
    }
}
