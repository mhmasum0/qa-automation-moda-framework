package com.moda.pages;


import com.moda.api.LoginEndPoints;
import com.moda.core.ShareData;
import com.moda.pages.base.BasePage;
import com.moda.utils.API;
import com.moda.utils.ConfigFileReader;
import com.moda.utils.ExplicitWait;
import com.moda.utils.LogHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DashboardPage extends BasePage {
    WebDriver driver;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String WELCOME_MESSAGE_XPATH = "//div[contains(text(),'welcome to your Member Dashboard')]";
    @FindBy(xpath = WELCOME_MESSAGE_XPATH)
    private WebElement welcomeMessage;

    @FindBy(xpath = "//button//span[text()='Moda 360']")
    private WebElement moda360Menu;

    @FindBy(xpath = "//button//span[text()='Find care']")
    private WebElement findCareMenu;

    @FindBy(xpath = "//button//span[text()='Moda 360']")
    private WebElement moda360MenuDev;

    @FindBy(xpath = "//span[@title='Moda 360 Programs']")
    private WebElement moda360ProgramsMenu;

    @FindBy(xpath = "(//a[@aria-label='Behavioral Health 360'])[1]")
    private WebElement behavioralHealth360Menu;

    @FindBy(xpath = "//span[text()='PCP 360']")
    private WebElement pcp360Menu;

    @FindBy(xpath = "//li//span[text()='Care Reminders' and @title='Care Reminders']")
    private WebElement careRemindersMenu;

    @Step("Welcome Dashboard")
    public String welcomeMessage(){
        String welcomeMessageText = "";
        try {
            new ExplicitWait(driver).waitForElement(60, By.xpath(WELCOME_MESSAGE_XPATH));
            welcomeMessageText = welcomeMessage.getText();
            LogHelper.getLogger().info("Dashboard Welcome text: {}", welcomeMessageText);
            Reporter.log("Dashboard Welcome text: " + welcomeMessageText);
        } catch (TimeoutException timeout){
            LogHelper.getLogger().info(timeout.getMessage());
        }
        return welcomeMessageText;
    }

    @Step("Click on Moda 360 Menu")
    public void clickOnModa360Menu(){
        String environment = API.extractEnvironment(ConfigFileReader.getConfigPropertyValue("url"));
        WebElement elementToClick = "dev".equals(environment) ? moda360MenuDev : moda360Menu;
        click(elementToClick, "Moda 360 Menu Clicked");
    }

    @Step("Click on Moda 360 Programs from menu")
    public void clickModa360ProgramMenu(){
        click(moda360ProgramsMenu,"Clicked on 360 programs menu");
    }

    @Step("Click on Behavioral Health 360 menu")
    public void clickOnBehavioralHealth360Menu(){
        click(behavioralHealth360Menu,"Clicked on Behavioral Health 360 menu");
    }

    @Step("Click on Care reminder menu")
    public void clickOnCareRemindersMenu(){
        click(careRemindersMenu, "click on Care Reminders menu");
    }

    @Step("Click on Find Care menu")
    public void clickOnFindCareMenu(){
        click(findCareMenu, "Clicked on Find Care menu");
    }

    @Step("Click on PCP 360 menu")
    public void clickOnPCP360Menu(){
        click(pcp360Menu, "Clicked on PCP 360 menu");
    }

    @Step("Go to next tab")
    public void goToNextTab(String originalTab){
        goToNextTab(driver, originalTab,  "Go to opened tab");
    }

    @Step("Back to original tab")
    public void backToOriginalTab(String originalTab){
        backToOriginalTab(driver, originalTab,  "Back to original tab");
    }

    @Step("Close the tab")
    public void closeTab(){
        closeTab(driver, "Close tab");
    }

    @Step("PCP Eligibility with API")
    public Response pcpEligibilityWithAPI(){
        String token = ShareData.getAccessToken();
        return LoginEndPoints.pcpEligibility(token);
    }

}
