package com.moda.utils;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class Tab {

    public static void goToNextTab(WebDriver driver, String originalTab){

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for(String handle : tabs){
            if(!handle.equals(originalTab)){
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public static void backToOriginalTab(WebDriver driver, String originalTab){
        driver.switchTo().window(originalTab);
    }

    public static void closeTab(WebDriver driver){
        driver.close();
    }
}
