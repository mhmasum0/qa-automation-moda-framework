package com.moda.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {
    WebDriver driver;

    public ExplicitWait(WebDriver driver){
        this.driver = driver;
    }

    public void waitForElement(int seconds, By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds) );
        wait.until(ExpectedConditions.presenceOfElementLocated( locator));
    }
}
