package com.moda.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scroll {

    private Scroll(){
        throw new IllegalStateException("This is utility class");
    }

    public static void scrollToElement(WebDriver driver, WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCode = "arguments[0].scrollIntoView({" +
                "behavior: 'smooth'," +
                "block: 'center'," +
                "inline: 'center'});";
        js.executeScript(jsCode, element);
        ExtraWaiting.extraWait(3);
    }

}
