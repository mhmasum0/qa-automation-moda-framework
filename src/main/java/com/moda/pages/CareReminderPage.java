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

    private static final String CARE_REMINDERS_HEADING_XPATH = "//div[text()='Care Reminders']";
    @FindBy(xpath = CARE_REMINDERS_HEADING_XPATH)
    private WebElement careRemindersHeading;

    private static final String ACTIVE_BUTTON_XPATH = "//div[text()='Active']";
    @FindBy(xpath = ACTIVE_BUTTON_XPATH)
    private WebElement activeButton;

    private static final String FIRST_CARET_REMINDER_LINK_XPATH = "(//vlocity_ins-block[contains(@data-conditions, 'state-condition-object') and contains(@data-element-label, 'block0clone0block3block2block0')])[2]//span//div";
    @FindBy(xpath = FIRST_CARET_REMINDER_LINK_XPATH)
    private WebElement firstCareReminder;

    @FindBy(xpath = "//span[text()='Cervical Cancer Screening']")
    private WebElement cervicalCancerScreening;

    private static final String SNOOZE_REMINDER_ACTION_XPATH = "//div[text()='Snooze this reminder']";
    @FindBy( xpath = SNOOZE_REMINDER_ACTION_XPATH)
    private WebElement snoozeReminderAction;

    @FindBy(xpath = "//span[text()='OK']")
    private WebElement okButton;

    @FindBy(xpath = "//div[text()='Snoozed']")
    private WebElement snoozedButton;

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
        return waitGettext(driver,careRemindersHeading,30, CARE_REMINDERS_HEADING_XPATH, "Confirm Care Reminders page");
    }

    @Step("Click on Active button")
    public void clickOnActiveButton() throws InterruptedException {
        Scroll.scrollToElement(driver, activeButton);
        click(activeButton, "Click on Active button");
    }

    @Step("Active Care Reminder with API")
    public Response activeCareReminderWithAPI(){
        String token = ShareData.getAccessToken();
        Response response = LoginEndPoints.activeCareReminder(token);
        LogHelper.getLogger().info("Active Care Reminder with API: {}",  response.getBody());
        return response;
    }

    @Step("Check the First Care Reminder")
    public void checkFirstCareReminder(){
        ResourceString.setFirstCareReminder(waitGettext(driver, firstCareReminder, 30, FIRST_CARET_REMINDER_LINK_XPATH, "Check the First Care Reminder"));
    }

    @Step("Click on First Care Reminder")
    public void clickOnFirstCareReminder() throws InterruptedException {
        Scroll.scrollToElement(driver, firstCareReminder);
        click(firstCareReminder, "Click on First Care Reminder");
    }

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
        waitClick(driver, snoozeReminderAction, 30, "Click on Snooze this reminder", SNOOZE_REMINDER_ACTION_XPATH);
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
        return waitGettext(driver, yearlyDentalExamAndCleaningSnoozed, 30, CARE_REMINDERS_HEADING_XPATH, "Confirm Yearly Dental Exam and Cleaning Snoozed");
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
