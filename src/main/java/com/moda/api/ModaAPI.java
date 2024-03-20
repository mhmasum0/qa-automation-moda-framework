package com.moda.api;

import com.moda.utils.API;
import com.moda.utils.ConfigFileReader;

public class ModaAPI {
    private ModaAPI() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static final String BASE_URL = API.parseBaseURL(ConfigFileReader.getConfigPropertyValue("url"));

    // Login
    protected static String postLoginUrl = BASE_URL +"/moda360gateway/api/token/authentication";

    // Care Reminder
    protected static String activeCareReminder = BASE_URL + "/moda360gateway/api/care-reminders?status=active";
}
