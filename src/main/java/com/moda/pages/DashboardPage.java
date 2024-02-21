package com.moda.pages;


import com.moda.utils.ExplicitWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DashboardPage.class);

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String welcomeMessageXpath = "//div[contains(text(),'welcome to your Member Dashboard')]";
    @FindBy(xpath = welcomeMessageXpath )
    private WebElement welcomeMessage;

    @FindBy(xpath = "//c-button[@id='menubutton-id-190']")
    private WebElement mainMenu360;

    @FindBy(xpath = "//span[@title='Moda 360 Programs']")
    private WebElement moda_360_programs_menu;

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

    @FindBy(xpath = "/html/body/div[3]/div[2]/div[2]/div/div/div/div/c-cf-mc360_-b-h360program_-detail-page/div/vlocity_ins-action[2]/c-modal/div/section/div/div/slot/div/slot/div/c-cf-mc360_-program-url_-popup/div/vlocity_ins-flex-card-state/div/slot/div/div/vlocity_ins-block/div/div/div/slot/div/div[3]/vlocity_ins-block/div/div/div/slot/div/div/vlocity_ins-output-field/div/lightning-formatted-rich-text/span/div/span")
    private WebElement leave;

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

    public void click360Menu(){
        mainMenu360.click();
    }

    public void clickModa360ProgramMenu(){
        moda_360_programs_menu.click();
    }

    public boolean visibility_360_programs_heading(){
        new ExplicitWait(driver).waitForElement(40,By.xpath(moda_360_programs_xpath));
        return moda_360_heading.isDisplayed();
    }

    public void clickBehavioral_health_360(){
        behavioral_health_360.click();
    }

    public void clickAddictionCare(){
        new ExplicitWait(driver).waitForElement(60,By.xpath(addiction_car_section_heading_xpath));
        addiction_care_section_heading.click();
    }

    public void clickLearnMore(){
        new ExplicitWait(driver).waitForElement(60,By.xpath(learn_more_xpath));
        learn_more_link.click();
    }

    public void clickOnCancel(){
        cancelButton.click();
    }

    public void clickLeave(){
        leave.click();
    }
}
