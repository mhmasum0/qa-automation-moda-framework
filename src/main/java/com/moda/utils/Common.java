package com.moda.utils;

import java.util.List;

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

    public static boolean listContainsElement(List<String> array, String target) {
        for (String element : array) {
            if (element.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static boolean compareStringLists(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }

        return true;
    }
}
