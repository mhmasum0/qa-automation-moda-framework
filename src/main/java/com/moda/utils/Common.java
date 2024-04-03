package com.moda.utils;

public class Common {

    private Common(){
        throw new IllegalStateException("Utility class");
    }

    public static boolean arrayContainsElement(String[] array, String target) {
        for (String element : array) {
            if (element.equals(target)) {
                return true;
            }
        }
        return false;
    }
}
