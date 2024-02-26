package com.moda.pages;

import com.moda.pages.base.BasePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareReminderPage  extends BasePage {
    WebDriver driver;
    Logger logger = LogManager.getLogger(this);

    public CareReminderPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String careRemindersHeadingXpath = "//div[text()='Care Reminders']";
    @FindBy(xpath = careRemindersHeadingXpath)
    private WebElement careRemindersHeading;

    // DEV
    @FindBy(xpath = "(//div[text()='Yearly Dental Exam and Cleaning'][1])[1]")
    private WebElement yearlyDentalReminderLink;

    // UAT
    @FindBy(xpath = "//span[text()='Cervical Cancer Screening']")
    private WebElement cervicalCancerScreening;

    // DEV
//    private static final String snoozeReminderActionXpath = "//div[text()='Snooze this reminder']";
//    @FindBy( xpath = snoozeReminderActionXpath)
//    private WebElement snoozeReminderAction;

    // UAT
    private static final String snoozeReminderActionXpath = "//span[text()='Snooze this reminder']";
    @FindBy( xpath = snoozeReminderActionXpath)
    private WebElement snoozeReminderAction;

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

    public String confirmCareRemindersPage(){
        return waitGettext(driver,careRemindersHeading,30,careRemindersHeadingXpath,logger,"Confirm Care Reminders page");
    }

    public void clickOnYearlyDentalReminderLink(){
        click(yearlyDentalReminderLink,logger,"Click on Year Dental Exam and Cleaning Reminder link");
    }

    public void clickOnCervicalCancerScreening(){
        click(cervicalCancerScreening,logger,"Click on Cervical Cancer Screening");
    }

    public void clickOnSnoozedCareReminders(){
        click(snoozedCareReminders,logger,"Click on Snoozed Care Reminders");
    }

    public void clickOnSnoozeReminder(){
        waitClick(driver, snoozeReminderAction, 30, logger, "Click on Snooze this reminder", snoozeReminderActionXpath);
    }

    public void clickOnOK(){
        click(okButton, logger, "Click on OK");
    }

    public void clickOnSnoozedButton(){
        click(snoozedButton, logger, "Click on Snoozed button");
    }

    public String confirmYearlyDentalExamAndCleaningSnoozed(){
        return waitGettext(driver, yearlyDentalExamAndCleaningSnoozed, 30, careRemindersHeadingXpath, logger, "Confirm Yearly Dental Exam and Cleaning Snoozed");
    }

    public void clickOnYearlyDentalExamAndCleaningSnoozed(){
        click(yearlyDentalExamAndCleaningSnoozed, logger, "Click on Yearly Dental Exam and Cleaning Snoozed");
    }

    public void clickOnUnsnoozeCareReminder(){
        click(unsnoozeCareReminder, logger, "Click on Unsnooze Care Reminder");
    }

    public void clickOnUnsnoozeButton(){
        click(unsnoozeButton, logger, "Click on Unsnooze button");
    }


}
