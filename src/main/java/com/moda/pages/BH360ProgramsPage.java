package com.moda.pages;

import com.moda.pages.base.BasePage;
import com.moda.utils.ExtraWaiting;
import com.moda.utils.Scroll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

import java.util.List;

public class BH360ProgramsPage extends BasePage {
    WebDriver driver;

    public BH360ProgramsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private static final String ADDICTION_CARR_SECTION_HEADING_XPATH = "//div[contains(text(),'Addiction care with Hazelden Betty Ford')]";
    @FindBy(xpath = ADDICTION_CARR_SECTION_HEADING_XPATH)
    private WebElement addictionCareSectionHeading;

    private static final String BH360_LATERAL_XPATH = "//div[@class='active']//span";
    @FindBy(xpath = BH360_LATERAL_XPATH)
    private WebElement h360Lateral;

    private static final String LEARN_MORE_XPATH = "//div[contains(text(),'Learn more and enroll')]";
    @FindBy(xpath = LEARN_MORE_XPATH)
    private WebElement learnMoreLink;

    private static final String HOPE_HEALTH_HEALING_XPATH = "//p[text()='Hope. Health. Healing.']";
    @FindBy(xpath = HOPE_HEALTH_HEALING_XPATH)
    private WebElement hopeHealthHealing;

    @FindBy(xpath = "//div[@class='slds-grid slds-wrap slds-card']//div[contains(@class,'sl-text-teal')]//span//div")
    private List<WebElement> programTitleList;

    private static final String PROGRAM_TITLE_XPATH = "//div[contains(@class,'sl-h3')]//span//div";
    private static final String LEARN_MORE_BUTTON_XPATH = "//div[contains(@class,'learn-btn')]";

    private static final String BHPROGRAM_BACK_BUTTON_XPATH = "//lightning-icon/following-sibling::span[text()='Behavioral Health 360 Services']";
    @FindBy(xpath = BHPROGRAM_BACK_BUTTON_XPATH)
    private WebElement bhprogramBackButton;

    @Step("Check if BH360 lateral is displayed")
    public boolean isBH360LateralDisplayed(){
        return isElementDisplayed(driver, 60, "Check if BH360 lateral is displayed", BH360_LATERAL_XPATH);
    }

    @Step("Check all the program is displayed")
    public List<String> programList() throws InterruptedException {
        Scroll.scrollToElement(driver, programTitleList.get(0));
        return getElementListText(programTitleList,  "All the services title");
    }

    @Step("Click on all the program")
    public void clickProgramList() throws InterruptedException {

        for (int i = 0; i < programTitleList.size(); i++) {
            Scroll.scrollToElement(driver, programTitleList.get(i));
            programTitleList.get(i).click();
            String programTitle = waitGettext(driver, driver.findElement(By.xpath(PROGRAM_TITLE_XPATH)), 60, PROGRAM_TITLE_XPATH, "Clicked on program");
            System.out.println("Program title: " + programTitle);
            ExtraWaiting.extraWait(2);

            boolean isLearnMoreButtonDisplayed = isElementDisplayed(driver, 2, "Check if learn more button is displayed", LEARN_MORE_BUTTON_XPATH);
            if (isLearnMoreButtonDisplayed) {
                driver.findElement(By.xpath(LEARN_MORE_BUTTON_XPATH)).click();
                ExtraWaiting.extraWait(2);
                this.clickOnCancel();
                driver.findElement(By.xpath(LEARN_MORE_BUTTON_XPATH)).click();
                ExtraWaiting.extraWait(2);
                this.clickLeavePopup();

                DashboardPage dashboardPage = new DashboardPage(driver);
                String originalTab = driver.getWindowHandle();
                dashboardPage.goToNextTab(originalTab);
                ExtraWaiting.extraWait(2);
                dashboardPage.closeTab();
                ExtraWaiting.extraWait(2);
                dashboardPage.backToOriginalTab(originalTab);
                ExtraWaiting.extraWait(2);
            }

            waitClick(driver, bhprogramBackButton, 60, "Click on back button", BHPROGRAM_BACK_BUTTON_XPATH);
            this.isBH360LateralDisplayed();
        }
    }

    @Step("Click on addiction care")
    public void clickAddictionCare() throws InterruptedException {
        Scroll.scrollToElement(driver, addictionCareSectionHeading);
        waitClick(driver, addictionCareSectionHeading,60,"Click on addiction care", ADDICTION_CARR_SECTION_HEADING_XPATH);
    }

    @Step("Click on learn more")
    public void clickLearnMore(){
        waitClick(driver, learnMoreLink,60, "Click on learn more", LEARN_MORE_XPATH);
    }

    public void clickOnCancel() {
        Moda360ProgramsPage moda360ProgramsPage = new Moda360ProgramsPage(driver);
        moda360ProgramsPage.clickOnCancel();
    }

    public void clickLeavePopup() {
        Moda360ProgramsPage moda360ProgramsPage = new Moda360ProgramsPage(driver);
        moda360ProgramsPage.clickLeavePopup();
    }

    @Step("Get main heading")
    public String getMainHeading(){
        return waitGettext(driver, hopeHealthHealing, 60, HOPE_HEALTH_HEALING_XPATH,  "Get main heading");
    }
}
