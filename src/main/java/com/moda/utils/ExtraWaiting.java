package com.moda.utils;

public class ExtraWaiting {

    private ExtraWaiting(){
        throw new IllegalStateException("This is utility class");
    }

    public static void extraWait(int seconds) throws InterruptedException {
        Thread.sleep(seconds* 1000L);
    }
}
