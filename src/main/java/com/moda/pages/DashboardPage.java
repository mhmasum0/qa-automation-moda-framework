package com.moda.pages;


import java.util.List;

import com.moda.pages.base.BasePage;
import com.moda.utils.ExplicitWait;

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
    private static final Logger logger = LogManager.getLogger(DashboardPage.class);

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

    private static final String moda_360_programs_xpath = "//div[contains(text(),'Moda 360 programs')][1]";
    @FindBy(xpath = moda_360_programs_xpath)
    private WebElement moda_360_heading;

    @FindBy(xpath = "//span[@data-value='BH360_Programs__c']")
    private WebElement behavioral_health_360;

    private static final String addiction_car_section_heading_xpath = "//div[contains(text(),'Addiction care with Hazelden Betty Ford')]";
    @FindBy(xpath = addiction_car_section_heading_xpath)
    private WebElement addiction_care_section_heading;

    private static final String learn_more_xpath = "//div[contains(text(),'Learn more and enroll')]";
    @FindBy(xpath = learn_more_xpath)
    private WebElement learn_more_link;

    @FindBy(xpath = "//span[@title='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[text()='Health coaching program']")
    private WebElement healthCoachingProgram;
    // //div[text()='Health coaching program']

    private static final String healthCoachingProgramHeadingXpath = "//div[contains(text(),'Health coaching program')]";
    @FindBy(xpath = healthCoachingProgramHeadingXpath)
    private WebElement healthCoachingProgramHeading;

    @FindBy(xpath = "//span[text()='Moda 360 Programs']/preceding-sibling::lightning-icon")
    private WebElement backModa360Programs;
    // //span[text()='Moda 360 Programs']/preceding-sibling::lightning-icon

    @FindBy(xpath = "//div[contains(text(),'Get extra dental benefits')]")
    private WebElement getExtraBenifits;

    @FindBy(xpath = "//span[contains(text(),'Health through Oral Wellness (PDF)')][1]")
    private List<WebElement> healthThroughOralWellNess;

    @FindBy(xpath = "//div/span[contains(text(),'Leave')]")
    private WebElement leavePopupButton;

    // //h1[text()='Hope. Health. Healing.']
    private static final String hopeHealthHealingXpath = "//h1[text()='Hope. Health. Healing.']";
    @FindBy(xpath = hopeHealthHealingXpath)
    private WebElement hopeHealthHealing;


    @FindBy(xpath = "//li//span[text()='Care Reminders' and @title='Care Reminders']")
    private WebElement careRemindersMenu;

    public String welcomeMessage(){
        String welcomeMessageText = "";
        try {
            new ExplicitWait(driver).waitForElement(60, By.xpath(welcomeMessageXpath));
            welcomeMessageText = welcomeMessage.getText();
            logger.info("Dashboard Welcome text: {}", welcomeMessageText); // Log the username input action
        } catch (TimeoutException timeout){
            timeout.printStackTrace();
        }
        return welcomeMessageText;
    }

    public void clickOnModa360Menu(){
        click(moda360Menu,logger,"Moda 360 Menu Clicked");
    }


    public void clickModa360ProgramMenu(){
        click(moda360ProgramsMenu,logger,"Clicked on 360 programs menu");
    }

    public void clickOnBehavioralHealth360Menu(){
        click(behavioralHealth360Menu,logger,"Clicked on Behavioral Health 360 menu");
    }

    public void visibility_360_programs_heading(){
        waitIsDisplayed(driver,moda_360_heading,40,logger," 360 programs displayed",moda_360_programs_xpath);
    }

    public void clickOnHealthCoachingProgram(){
        click(healthCoachingProgram,logger,"Click on health program");
    }

    public void checkHealthCoachingProgramHeading(){
        waitIsDisplayed(driver,healthCoachingProgramHeading,40,logger,"Check health coaching program heading",healthCoachingProgramHeadingXpath);
    }

    public void clickOnBackModa360Programs(){
        click(backModa360Programs,logger,"Back from Health programs");
    }

    public void clickOnGetExtraBenifits(){
        click(getExtraBenifits,logger,"Click on Get Extra Benifits link");
    }

    public void clickOnHealthThroughOraWellness(){
        clickWithIndex(healthThroughOralWellNess,1,logger,"Click on Health thought Oral Wellness(PDF)");
    }

    public void clickLeavePopup(){
        click(leavePopupButton,logger,"Click on Leave");
    }

    public void goToNextTab(String originalTab){
        goToNextTab(driver, originalTab, logger, "Go to opened tab");
    }

    public String getMainHeading(){
        return waitGettext(driver, hopeHealthHealing, 60, hopeHealthHealingXpath, logger, "Get main heading");
    }

    public String getPDFContent(String pdfURL) throws Exception {
        return readPDFContent(driver, pdfURL, logger, "Read PDF content");
    }

    public void backToOriginalTab(String originalTab){
        backToOriginalTab(driver, originalTab, logger, "Back to original tab");
    }

    public void closeTab(){
        closeTab(driver, logger, "Close tab");
    }

    public void clickBehavioral_health_360(){
        click(behavioral_health_360, logger, "Click Behavioral health 360");
    }

    public void clickAddictionCare(){
        waitClick(driver,addiction_care_section_heading,60, logger,"Click on addiction care", addiction_car_section_heading_xpath);
    }

    public void clickLearnMore(){
        waitClick(driver,learn_more_link,60,logger,"Click on learn more", learn_more_xpath);
    }

    public void clickOnCancel(){
        click(cancelButton,logger,"Click on cancel");
    }

    public void switchToNewTab(String originalTab){
       goToNextTab(driver , originalTab, logger, "Switch to new tab");
    }

    public void clickOnCareRemindersMenu(){
        click(careRemindersMenu,logger,"click on Care Reminders menu");
    }
}
