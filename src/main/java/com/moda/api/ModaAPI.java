package com.moda.api;

import com.moda.utils.API;
import com.moda.utils.ConfigFileReader;

public class ModaAPI {
    public static String base_url = API.parseBaseURL(ConfigFileReader.getConfigPropertyValue("url"));

    // Login
    public static String post_login_url = base_url +"/moda360gateway/api/token/authentication";

    // Care Reminder
    public static String active_care_reminder = base_url + "/moda360gateway/api/care-reminders?status=active";

}
