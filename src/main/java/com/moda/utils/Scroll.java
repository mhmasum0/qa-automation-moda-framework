package com.moda.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Scroll {

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
