package com.moda.core;

import com.moda.utils.ConfigFileReader;

import java.nio.file.Paths;

public class Constants {

    private Constants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/screenshots";
    public static final String CONFIG_PATH = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "config.properties").toString();
    public static final String URL = ConfigFileReader.getConfigPropertyValue("url");
    public static final String USER = ConfigFileReader.getConfigPropertyValue("username");
    public static final String PASSWORD = ConfigFileReader.getConfigPropertyValue("password");
    public static final String WRONG_PASSWORD = ConfigFileReader.getConfigPropertyValue("wrong_password");
}
