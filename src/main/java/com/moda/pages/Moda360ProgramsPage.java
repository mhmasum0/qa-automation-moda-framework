package com.moda.pages;

import com.moda.pages.base.BasePage;
import com.moda.utils.Scroll;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Moda360ProgramsPage extends BasePage {
    WebDriver driver;

    public Moda360ProgramsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String moda_360_programs_xpath = "//div[contains(text(),'Moda 360 programs')][1]";
    @FindBy(xpath = moda_360_programs_xpath)
    private WebElement moda_360_heading;

    @FindBy(xpath = "//div[text()='Health coaching program']")
    private WebElement healthCoachingProgram;

    private static final String healthCoachingProgramHeadingXpath = "//div[contains(text(),'Health coaching program')]";
    @FindBy(xpath = healthCoachingProgramHeadingXpath)
    private WebElement healthCoachingProgramHeading;

    @FindBy(xpath = "//span[text()='Moda 360 Programs']/preceding-sibling::lightning-icon")
    private WebElement backModa360Programs;

    @FindBy(xpath = "//div[contains(text(),'Get extra dental benefits')]")
    private WebElement getExtraBenefits;

    @FindBy(xpath = "//span[contains(text(),'Health through Oral Wellness (PDF)')][1]")
    private List<WebElement> healthThroughOralWellNess;

    @FindBy(xpath = "//div/span[contains(text(),'Leave')]")
    private WebElement leavePopupButton;

    @FindBy(xpath = "//span[@title='Cancel']")
    private WebElement cancelButton;

    @Step("360 programs displayed")
    public void visibility_360_programs_heading(){
        waitIsDisplayed(driver,moda_360_heading,40,"360 programs displayed",moda_360_programs_xpath);
    }

    @Step("Click on health program")
    public void clickOnHealthCoachingProgram() throws InterruptedException {
        Scroll.scrollToElement(driver,healthCoachingProgram);
        click(healthCoachingProgram,"Click on health program");
    }

    @Step("Check health coaching program heading")
    public void checkHealthCoachingProgramHeading() {
        waitIsDisplayed(driver,healthCoachingProgramHeading,40,"Check health coaching program heading",healthCoachingProgramHeadingXpath);
    }

    @Step("Click on back to Moda 360 programs")
    public void clickOnBackModa360Programs(){
        click(backModa360Programs,"Back from Health programs");
    }

    @Step("Click on Get Extra Benefits")
    public void clickOnGetExtraBenefits() throws InterruptedException {
        Scroll.scrollToElement(driver, getExtraBenefits);
        click(getExtraBenefits,"Click on Get Extra Benifits link");
    }

    @Step("Click on Health Through Oral Wellness(PDF)")
    public void clickOnHealthThroughOraWellness() throws InterruptedException {
        var el = healthThroughOralWellNess.get(1);
        Scroll.scrollToElement(driver, el);
        clickWithIndex(healthThroughOralWellNess,1,"Click on Health thought Oral Wellness(PDF)");
    }

    @Step("Click on Cancel")
    public void clickOnCancel(){
        click(cancelButton, "Click on cancel");
    }

    @Step("Click on Leave")
    public void clickLeavePopup(){
        click(leavePopupButton,"Click on Leave");
    }

    @Step("Get the PDF Content")
    public String getPDFContent(String url) throws Exception {
        return readPDFContent(driver,url,"Get the PDF content");
    }

}
