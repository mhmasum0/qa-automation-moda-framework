package com.moda.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClearCache {
    private ClearCache(){
        throw new IllegalStateException("Utility class");
    }

    public static void clearEdgeCache(WebDriver driver){
        driver.get("edge://settings/clearBrowserData");
        WebElement clear = driver.findElement(By.cssSelector("#clear-now"));
        clear.click();
    }

    public static void clearChromeCache(WebDriver driver) throws InterruptedException {
        driver.get("chrome://settings/clearBrowserData");
        WebElement shadowElement = driver
                .findElement(By.cssSelector("settings-ui"))
                .getShadowRoot()
                .findElement(By.cssSelector("settings-main"))
                .getShadowRoot()
                .findElement(By.cssSelector("settings-basic-page"))
                .getShadowRoot()
                .findElement(By.cssSelector("settings-privacy-page"))
                .getShadowRoot()
                .findElement(By.cssSelector("settings-clear-browsing-data-dialog"))
                .getShadowRoot()
                .findElement(By.cssSelector("#clearBrowsingDataConfirm"));
        ExtraWaiting.extraWait(4);
        shadowElement.click();
        ExtraWaiting.extraWait(3);
    }
}
