package com.moda.pages.base;

import java.io.IOException;
import java.util.List;

import com.moda.utils.ExplicitWait;
import com.moda.utils.LogHelper;
import com.moda.utils.PDFReader;
import com.moda.utils.Tab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class BasePage {
    protected void click(WebElement element, String log){
        element.click();
        LogHelper.getLogger().info(log);
        Reporter.log(log);
    }

    protected void clickWithIndex(List<WebElement> element, int index, String log){
        element.get(index).click();
        LogHelper.getLogger().info(log);
        Reporter.log(log);
    }

    protected void input(WebElement element,String input){
        element.sendKeys(input);
        LogHelper.getLogger().info("Inputting username: {}", input);
        Reporter.log("Inputting username: "+ input);
    }

    protected void waitClick(WebDriver driver, WebElement element, int seconds, String log, String locator){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(locator));
        click(element,log);
    }

    protected boolean isElementDisplayed(WebDriver driver, int seconds, String log, String locator){
        boolean isDisplayed = new ExplicitWait(driver).isElementDisplayed(seconds, By.xpath(locator));
        LogHelper.getLogger().info(log);
        Reporter.log(log);
        return isDisplayed;
    }

    protected String waitGettext(WebDriver driver, WebElement element, int seconds, String xpath, String log){
        new ExplicitWait(driver).waitForElement(seconds,By.xpath(xpath));
        LogHelper.getLogger().info(log);
        Reporter.log(log);
        return element.getText();
    }

    protected void goToNextTab(WebDriver driver, String originalTab, String log){
        Tab.goToNextTab(driver, originalTab);
        LogHelper.getLogger().info(log);
        Reporter.log(log);
    }

    protected void backToOriginalTab(WebDriver driver, String originalTab, String log){
        Tab.backToOriginalTab(driver, originalTab);
        LogHelper.getLogger().info(log);
        Reporter.log(log);
    }

    protected void closeTab(WebDriver driver, String log){
        Tab.closeTab(driver);
        LogHelper.getLogger().info(log);
        Reporter.log(log);
    }

    protected String readPDFContent(String pdfURL, String log)  {
        String pdfContent;
        try {
            pdfContent = PDFReader.readPDFContent(pdfURL);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read PDF");
        }
        LogHelper.getLogger().info(log);
        Reporter.log(log);
        return pdfContent;
    }

}
