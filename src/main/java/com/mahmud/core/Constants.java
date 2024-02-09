package com.mahmud.core;

import com.mahmud.utils.ConfigFileReader;

import java.nio.file.Paths;

public class Constants {
    public static final String CONFIG_PATH = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "config.properties").toString();
    public static final String URL = ConfigFileReader.getConfigPropertyValue("url");
    public static final String USER = ConfigFileReader.getConfigPropertyValue("username");
    public static final String PASSWORD = ConfigFileReader.getConfigPropertyValue("password");
    public static final String WRONG_PASSWORD = ConfigFileReader.getConfigPropertyValue("wrongp_assword");
}
