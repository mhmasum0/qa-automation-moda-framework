package com.moda.utils;

public class ExtraWaiting {
    public static void extraWait(int seconds) throws InterruptedException {

        Thread.sleep(seconds* 1000L);
    }
}
