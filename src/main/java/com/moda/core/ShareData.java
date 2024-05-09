package com.moda.core;

public class ShareData {

    private ShareData() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    protected static ThreadLocal<String> accessToken = new ThreadLocal<>();

    public static void setAccessToken(String value){
        accessToken.set(value);
    }

    public static String getAccessToken(){
        return accessToken.get();
    }


}
