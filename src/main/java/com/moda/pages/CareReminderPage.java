package com.moda.pages;

import com.moda.api.LoginEndPoints;
import com.moda.core.ResourceString;
import com.moda.core.ShareData;
import com.moda.pages.base.BasePage;
import com.moda.utils.Scroll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

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

    private static final String FIRST_CARE_REMINDER_LINK_XPATH = "(//vlocity_ins-block[contains(@data-conditions, 'state-condition-object')]//div[ contains(@data-style-id, 'state0element0block_element0block_element0')]//span//div)[1]";
    @FindBy(xpath = FIRST_CARE_REMINDER_LINK_XPATH)
    private WebElement firstCareReminder;

    private static final String SNOOZED_CARE_REMINDER_TITLE_XPATH = "((//div[@class='slds-col condition-element slds-size_12-of-12'])[2]//span/div)[1]";
    @FindBy(xpath = SNOOZED_CARE_REMINDER_TITLE_XPATH)
    private WebElement snoozedCareReminder;

    private static final String ACTIVE_SNOOZED_XPATH = "//div[contains(@class,'btn active')]//div[text()='Snoozed']";

    @FindBy(xpath = "//div[text()='Completed']")
    private WebElement completedButton;

    @FindBy(xpath = "(//div[@class='slds-col condition-element slds-size_12-of-12'])[2]//vlocity_ins-flex-card-state//div[contains(@class,'sl-text-teal')]")
    private List<WebElement> completedCareReminder;

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

    private static final String SNOOZED_CARE_REMINDERS_HEADER_XPATH = "//div[text()='Snoozed Care Reminders']";

    @FindBy(xpath = "//lightning-icon/following-sibling::span[text()='Care Reminders']")
    private WebElement careRemindersBackButton;

    @FindBy(xpath = "//div[text()='Unsnooze Care Reminder']")
    private WebElement unsnoozeCareReminderButton;

    @Step("Check all the completed care reminder")
    public List<String> completedCareReminder(){
        return getElementListText(completedCareReminder,  "All the completed care reminder title");
    }

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
        return LoginEndPoints.activeCareReminder(token);
    }

    @Step("Snoozed Care Reminder with API")
    public Response snoozedCareReminderWithAPI(){
        String token = ShareData.getAccessToken();
        return LoginEndPoints.snoozedCareReminder(token);
    }

    @Step("Snooze Reminder Header")
    public void snoozeReminderHeader(){
        isElementDisplayed(driver, 30, "Snooze Reminder Header", SNOOZED_CARE_REMINDERS_HEADER_XPATH);
    }

    @Step("Click on Care Reminders Back Button")
    public void clickOnCareRemindersBackButton() throws InterruptedException {
        Scroll.scrollToElement(driver, careRemindersBackButton);
        click(careRemindersBackButton, "Click on Care Reminders Back Button");
    }

    @Step("Completed Care Reminder with API")
    public Response completedCareReminderWithAPI(){
        String token = ShareData.getAccessToken();
        return LoginEndPoints.completedCareReminder(token);
    }

    @Step("Check the First Care Reminder")
    public void checkFirstCareReminder(){
        ResourceString.setFirstCareReminder(waitGettext(driver, firstCareReminder, 30, FIRST_CARE_REMINDER_LINK_XPATH, "Check the First Care Reminder"));
    }

    @Step("Check the Active Snoozed")
    public void checkActiveSnoozed(){
        isElementDisplayed(driver, 20, "Check the Active Snoozed", ACTIVE_SNOOZED_XPATH);
    }

    @Step("Check the Snoozed Care Reminder")
    public void checkSnoozedCareReminder(){
        String snoozedCareReminderr = waitGettext(driver, snoozedCareReminder, 30, SNOOZED_CARE_REMINDER_TITLE_XPATH, "Check the Snoozed Care Reminder");
        ResourceString.setSnoozedCareReminder(snoozedCareReminderr);
    }

    @Step("Click on the Snoozed Care Reminder")
    public void clickOnSnoozedCareReminder() throws InterruptedException {
        Scroll.scrollToElement(driver, snoozedCareReminder);
        click(snoozedCareReminder, "Click on the Snoozed Care Reminder");
    }

    @Step("Click on UnSnoozed button")
    public void clickOnUnSnoozedCareReminderButton() throws InterruptedException {
        Scroll.scrollToElement(driver, unsnoozeCareReminderButton);
        click(unsnoozeCareReminderButton, "Click on UnSnoozed button");
    }

    @Step("Click on Completed button")
    public void clickOnCompletedButton() throws InterruptedException {
        Scroll.scrollToElement(driver, completedButton);
        click(completedButton, "Click on Snoozed button");
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
