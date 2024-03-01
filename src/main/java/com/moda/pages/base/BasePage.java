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
    protected void click(WebElement element, String log){
        element.click();
        LogHelper.getLogger().info(log);
    }

    protected void clickWithIndex(List<WebElement> element, int index, String log){
        element.get(index).click();
        LogHelper.getLogger().info(log);
    }

    protected void input(WebElement element,String input, String log){
        element.sendKeys(input);
        LogHelper.getLogger().info("Inputting username: {}", input);
    }

    protected void waitClick(WebDriver driver, WebElement element, int seconds, String log, String locator){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(locator));
        click(element,log);
    }

    protected void waitIsDisplayed(WebDriver driver, WebElement element, int seconds, String log, String locator){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(locator));
        element.isDisplayed();
        LogHelper.getLogger().info(log);
    }

    protected String waitGettext(WebDriver driver, WebElement element, int seconds, String xpath, String log){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(xpath));
        LogHelper.getLogger().info(log);
        return element.getText();
    }

    protected void goToNextTab(WebDriver driver, String originalTab, String log){
        Tab.goToNextTab(driver, originalTab);
        LogHelper.getLogger().info(log);
    }

    protected void backToOriginalTab(WebDriver driver, String originalTab, String log){
        Tab.backToOriginalTab(driver, originalTab);
        LogHelper.getLogger().info(log);
    }

    protected void closeTab(WebDriver driver, String log){
        Tab.closeTab(driver);
        LogHelper.getLogger().info(log);
    }

    protected String readPDFContent(WebDriver driver, String pdfURL, String log) throws Exception {
        String pdfContent = PDFReader.ReadPDFContent(pdfURL);
        LogHelper.getLogger().info(log);
        return pdfContent;
    }

}
