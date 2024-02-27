package com.moda.pages.base;

import java.util.List;

import com.moda.utils.ExplicitWait;
import com.moda.utils.LogHelper;
import com.moda.utils.PDFReader;
import com.moda.utils.Tab;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Allure;

public class BasePage {
    public void click(WebElement element, String log){
        Allure.step(log);
        element.click();
        LogHelper.getLogger().info(log);
    }

    public void clickWithIndex(List<WebElement> element, int index, String log){
        element.get(index).click();
        Allure.step(log);
        LogHelper.getLogger().info(log);
    }

    public void input(WebElement element,String input, String log){
        Allure.step(log);
        Allure.parameter("userName", input);
        element.sendKeys(input);
        LogHelper.getLogger().info("Inputting username: {}", input);
    }

    public void waitClick(WebDriver driver, WebElement element, int seconds, String log, String locator){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(locator));
        click(element,log);
    }

    public void waitIsDisplayed(WebDriver driver, WebElement element, int seconds, String log, String locator){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(locator));
        element.isDisplayed();
        Allure.step(log);
        LogHelper.getLogger().info(log);
    }

    public String waitGettext(WebDriver driver, WebElement element, int seconds, String xpath, String log){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(xpath));
        Allure.step(log);
        LogHelper.getLogger().info(log);
        return element.getText();
    }

    public void goToNextTab(WebDriver driver, String originalTab, String log){
        Tab.goToNextTab(driver, originalTab);
        Allure.step(log);
        LogHelper.getLogger().info(log);
    }

    public void backToOriginalTab(WebDriver driver, String originalTab, String log){
        Tab.backToOriginalTab(driver, originalTab);
        Allure.step(log);
        LogHelper.getLogger().info(log);
    }

    public void closeTab(WebDriver driver, String log){
        Tab.closeTab(driver);
        Allure.step(log);
        LogHelper.getLogger().info(log);
    }

    public String readPDFContent(WebDriver driver, String pdfURL, String log) throws Exception {
        String pdfContent = PDFReader.ReadPDFContent(pdfURL);
        Allure.step(log);
        LogHelper.getLogger().info(log);
        return pdfContent;
    }

}
