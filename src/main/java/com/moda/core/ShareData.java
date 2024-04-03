package com.moda.core;

public class ShareData {

    private ShareData() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static String accessToken;

    public static void setAccessToken(String value){
        accessToken = value;
    }

    public static String getAccessToken(){
        return accessToken;
    }


}
