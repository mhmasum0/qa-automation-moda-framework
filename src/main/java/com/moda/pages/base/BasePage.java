package com.moda.pages.base;

import com.moda.utils.ExplicitWait;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {
    public void click(WebElement element, Logger logger,String log){
        Allure.step(log);
        element.click();
        logger.info(log);
    }

    public void clickWithIndex(List<WebElement> element, int index, Logger logger, String log){
        element.get(index).click();
        Allure.step(log);
        logger.info(log);
    }

    public void input(WebElement element,String input, Logger logger, String log){
        Allure.step(log);
        Allure.parameter("userName", input);
        element.sendKeys(input);
        logger.info("Inputting username: {}", input);
    }

    public void waitAndClick(WebDriver driver,WebElement element, int seconds, Logger logger, String log, String locator){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(locator));
        click(element,logger,log);
    }

    public void waitAndisDisplayed(WebDriver driver, WebElement element, int seconds, Logger logger, String log, String locator){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(locator));
        element.isDisplayed();
        Allure.step(log);
        logger.info(log);
    }

}
