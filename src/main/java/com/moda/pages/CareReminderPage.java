package com.moda.pages;

import com.moda.api.LoginEndPoints;
import com.moda.core.ResourceString;
import com.moda.core.ShareData;
import com.moda.pages.base.BasePage;

import com.moda.utils.LogHelper;
import com.moda.utils.Scroll;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareReminderPage  extends BasePage {
    WebDriver driver;

    public CareReminderPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String careRemindersHeadingXpath = "//div[text()='Care Reminders']";
    @FindBy(xpath = careRemindersHeadingXpath)
    private WebElement careRemindersHeading;

    private static final String activeButtonXpath = "//div[text()='Active']";
    @FindBy(xpath = activeButtonXpath)
    private WebElement activeButton;

    // DEV
    // private static final String yearlyDentalReminderLinkXpath = "(//div[text()='Yearly Dental Exam and Cleaning'][1])[1]";
    // @FindBy(xpath = yearlyDentalReminderLinkXpath)
    // private WebElement yearlyDentalReminderLink;

    // First Care Reminder Xpath
    private static final String firstCaretReminderLinkXpath = "(//vlocity_ins-block[contains(@data-conditions, 'state-condition-object') and contains(@data-element-label, 'block0clone0block3block2block0')])[2]//span//div";
    @FindBy(xpath = firstCaretReminderLinkXpath)
    private WebElement firstCareReminder;

    // UAT
    @FindBy(xpath = "//span[text()='Cervical Cancer Screening']")
    private WebElement cervicalCancerScreening;

    // DEV
    private static final String snoozeReminderActionXpath = "//div[text()='Snooze this reminder']";
    @FindBy( xpath = snoozeReminderActionXpath)
    private WebElement snoozeReminderAction;

//    // UAT
//    private static final String snoozeReminderActionXpath = "//span[text()='Snooze this reminder']";
//    @FindBy( xpath = snoozeReminderActionXpath)
//    private WebElement snoozeReminderAction;

    @FindBy(xpath = "//span[text()='OK']")
    private WebElement okButton;

    @FindBy(xpath = "//div[text()='Snoozed']")
    private WebElement snoozedButton;

    // UAT
    @FindBy(xpath = "//span[text()='Snoozed Care Reminders']")
    private WebElement snoozedCareReminders;

    @FindBy( xpath = "//div[text()='Yearly Dental Exam and Cleaning'][1]")
    private WebElement yearlyDentalExamAndCleaningSnoozed;

    @FindBy(xpath = "//div[text()='Unsnooze Care Reminder']")
    private WebElement unsnoozeCareReminder;

    @FindBy(xpath = "//button//span[text()='Unsnooze']")
    private WebElement unsnoozeButton;

    @Step("Confirm Care Reminders page")
    public String confirmCareRemindersPage(){
        return waitGettext(driver,careRemindersHeading,30,careRemindersHeadingXpath, "Confirm Care Reminders page");
    }

    @Step("Click on Active button")
    public void clickOnActiveButton() throws InterruptedException {
        Scroll.scrollToElement(driver, activeButton);
        click(activeButton, "Click on Active button");
    }

    @Step("Active Care Reminder with API")
    public Response activeCareReminderWithAPI(){
        String token = ShareData.accessToken;
        Response response = LoginEndPoints.activeCareReminder(token);
        LogHelper.getLogger().info("Active Care Reminder with API: " + response.getBody().print());
        return response;
    }

    // Check the First Care Reminder
    @Step("Check the First Care Reminder")
    public void checkFirstCareReminder(){
        String firstCareReminderText = waitGettext(driver, firstCareReminder, 30, firstCaretReminderLinkXpath, "Check the First Care Reminder");
        ResourceString.FIRST_CARE_REMINDER = firstCareReminderText;
    }

    // Click the First Care Reminder
    @Step("Click on First Care Reminder")
    public void clickOnFirstCareReminder() throws InterruptedException {
        Scroll.scrollToElement(driver, firstCareReminder);
        click(firstCareReminder, "Click on First Care Reminder");
    }

//    @Step("Click on Year Dental Exam and Cleaning Reminder link")
//    public void clickOnYearlyDentalReminderLink() throws InterruptedException {
//        Scroll.scrollToElement(driver, yearlyDentalReminderLink);
//        waitClick(driver,yearlyDentalReminderLink, 30 ,"Click on Year Dental Exam and Cleaning Reminder link", yearlyDentalReminderLinkXpath);
//    }

    @Step("Click on Cervical Cancer Screening")
    public void clickOnCervicalCancerScreening() throws InterruptedException {
        Scroll.scrollToElement(driver, cervicalCancerScreening);
        click(cervicalCancerScreening, "Click on Cervical Cancer Screening");
    }

    @Step("Click on Snoozed Care Reminders")
    public void clickOnSnoozedCareReminders(){
        click(snoozedCareReminders, "Click on Snoozed Care Reminders");
    }

    @Step("Click on Snooze this reminder")
    public void clickOnSnoozeReminder() throws InterruptedException {
        Scroll.scrollToElement(driver,snoozeReminderAction);
        waitClick(driver, snoozeReminderAction, 30, "Click on Snooze this reminder", snoozeReminderActionXpath);
    }

    @Step("Click on OK")
    public void clickOnOK(){
        click(okButton, "Click on OK");
    }

    @Step("Click on Snoozed button")
    public void clickOnSnoozedButton() throws InterruptedException {
        Scroll.scrollToElement(driver, snoozedButton);
        click(snoozedButton, "Click on Snoozed button");
    }

    @Step("Confirm Yearly Dental Exam and Cleaning Snoozed")
    public String confirmYearlyDentalExamAndCleaningSnoozed(){
        return waitGettext(driver, yearlyDentalExamAndCleaningSnoozed, 30, careRemindersHeadingXpath, "Confirm Yearly Dental Exam and Cleaning Snoozed");
    }

    @Step("Click on Yearly Dental Exam and Cleaning Snoozed")
    public void clickOnYearlyDentalExamAndCleaningSnoozed() throws InterruptedException {
        Scroll.scrollToElement(driver, yearlyDentalExamAndCleaningSnoozed);
        click(yearlyDentalExamAndCleaningSnoozed, "Click on Yearly Dental Exam and Cleaning Snoozed");
    }

    @Step("Click on Unsnooze Care Reminder")
    public void clickOnUnsnoozeCareReminder() throws InterruptedException {
        Scroll.scrollToElement(driver, unsnoozeCareReminder);
        click(unsnoozeCareReminder, "Click on Unsnooze Care Reminder");
    }

    @Step("Click on Unsnooze button")
    public void clickOnUnsnoozeButton(){
        click(unsnoozeButton, "Click on Unsnooze button");
    }


}
