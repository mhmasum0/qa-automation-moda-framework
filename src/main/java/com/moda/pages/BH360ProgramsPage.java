package com.moda.pages;

import com.moda.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BH360ProgramsPage extends BasePage {
    WebDriver driver;

    public BH360ProgramsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private static final String addiction_car_section_heading_xpath = "//div[contains(text(),'Addiction care with Hazelden Betty Ford')]";
    @FindBy(xpath = addiction_car_section_heading_xpath)
    private WebElement addiction_care_section_heading;

    private static final String learn_more_xpath = "//div[contains(text(),'Learn more and enroll')]";
    @FindBy(xpath = learn_more_xpath)
    private WebElement learn_more_link;

    private static final String hopeHealthHealingXpath = "//h1[text()='Hope. Health. Healing.']";
    @FindBy(xpath = hopeHealthHealingXpath)
    private WebElement hopeHealthHealing;


    public void clickAddictionCare(){
        waitClick(driver,addiction_care_section_heading,60,"Click on addiction care", addiction_car_section_heading_xpath);
    }

    public void clickLearnMore(){
        waitClick(driver,learn_more_link,60, "Click on learn more", learn_more_xpath);
    }

    public void clickOnCancel() {
        Moda360ProgramsPage moda360ProgramsPage = new Moda360ProgramsPage(driver);
        moda360ProgramsPage.clickOnCancel();
    }

    public void clickLeavePopup() {
        Moda360ProgramsPage moda360ProgramsPage = new Moda360ProgramsPage(driver);
        moda360ProgramsPage.clickLeavePopup();
    }

    public String getMainHeading(){
        return waitGettext(driver, hopeHealthHealing, 60, hopeHealthHealingXpath,  "Get main heading");
    }
}
