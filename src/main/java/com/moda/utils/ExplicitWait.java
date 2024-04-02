package com.moda.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {
    WebDriver driver;

    public ExplicitWait(WebDriver driver){
        this.driver = driver;
    }

    public void waitForElement(int seconds, By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds) );
        wait.until(ExpectedConditions.presenceOfElementLocated( locator));
    }

    public boolean isElementDisplayed(int seconds, By locator){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
