package com.moda.api;

import com.moda.utils.API;
import com.moda.utils.ConfigFileReader;

public class Routes {
    public static String base_url = API.parseBaseURL(ConfigFileReader.getConfigPropertyValue("url"));

    // Login
    public static String post_login_url = base_url +"/moda360gateway/api/token/authentication";

}
