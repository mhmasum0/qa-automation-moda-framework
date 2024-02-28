package com.moda.pages;


import java.util.List;

import com.moda.pages.base.BasePage;
import com.moda.utils.ExplicitWait;

import com.moda.utils.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {
    WebDriver driver;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String welcomeMessageXpath = "//div[contains(text(),'welcome to your Member Dashboard')]";
    @FindBy(xpath = welcomeMessageXpath )
    private WebElement welcomeMessage;

    @FindBy(xpath = "//button//span[text()='Moda 360']")
    private WebElement moda360Menu;

    @FindBy(xpath = "//span[@title='Moda 360 Programs']")
    private WebElement moda360ProgramsMenu;

    @FindBy(xpath = "(//a[@aria-label='Behavioral Health 360'])[1]")
    private WebElement behavioralHealth360Menu;

    private static final String addiction_car_section_heading_xpath = "//div[contains(text(),'Addiction care with Hazelden Betty Ford')]";
    @FindBy(xpath = addiction_car_section_heading_xpath)
    private WebElement addiction_care_section_heading;

    private static final String learn_more_xpath = "//div[contains(text(),'Learn more and enroll')]";
    @FindBy(xpath = learn_more_xpath)
    private WebElement learn_more_link;

    @FindBy(xpath = "//li//span[text()='Care Reminders' and @title='Care Reminders']")
    private WebElement careRemindersMenu;

    public String welcomeMessage(){
        String welcomeMessageText = "";
        try {
            new ExplicitWait(driver).waitForElement(60, By.xpath(welcomeMessageXpath));
            welcomeMessageText = welcomeMessage.getText();
            LogHelper.getLogger().info("Dashboard Welcome text: {}", welcomeMessageText); // Log the username input action
        } catch (TimeoutException timeout){
            timeout.printStackTrace();
        }
        return welcomeMessageText;
    }

    public void clickOnModa360Menu(){
        click(moda360Menu,"Moda 360 Menu Clicked");
    }


    public void clickModa360ProgramMenu(){
        click(moda360ProgramsMenu,"Clicked on 360 programs menu");
    }

    public void clickOnBehavioralHealth360Menu(){
        click(behavioralHealth360Menu,"Clicked on Behavioral Health 360 menu");
    }

    public void goToNextTab(String originalTab){
        goToNextTab(driver, originalTab,  "Go to opened tab");
    }

    public void backToOriginalTab(String originalTab){
        backToOriginalTab(driver, originalTab,  "Back to original tab");
    }

    public void closeTab(){
        closeTab(driver, "Close tab");
    }

    public void clickOnCareRemindersMenu(){
        click(careRemindersMenu, "click on Care Reminders menu");
    }
}
