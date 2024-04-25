package com.moda.pages;

import com.moda.pages.base.BasePage;
import com.moda.utils.Scroll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

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
    private WebElement H360Lateral;

    private static final String LEARN_MORE_XPATH = "//div[contains(text(),'Learn more and enroll')]";
    @FindBy(xpath = LEARN_MORE_XPATH)
    private WebElement learnMoreLink;

    private static final String HOPE_HEALTH_HEALING_XPATH = "//h1[text()='Hope. Health. Healing.']";
    @FindBy(xpath = HOPE_HEALTH_HEALING_XPATH)
    private WebElement hopeHealthHealing;

    @Step("Check if BH360 lateral is displayed")
    public boolean isBH360LateralDisplayed(){
        return isElementDisplayed(driver, 60, "Check if BH360 lateral is displayed", BH360_LATERAL_XPATH);
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
