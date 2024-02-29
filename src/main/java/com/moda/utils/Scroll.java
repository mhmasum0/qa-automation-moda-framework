package com.moda.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scroll {
    public static void scrollToElement(WebDriver driver, WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String js_code = "arguments[0].scrollIntoView({" +
                "behavior: 'smooth'," +  // defines the transition animation
                "block: 'center'," +   // vertically aligns at center
                "inline: 'center'});";  // horizontally aligns at center (if needed)
        js.executeScript(js_code, element);
        ExtraWaiting.extraWait(3);
    }

}
