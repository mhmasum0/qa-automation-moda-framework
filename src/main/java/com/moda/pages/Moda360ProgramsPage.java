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

    private static final String MODA_360_PROGRAMS_XPATH = "//div[contains(text(),'Moda 360 programs')][1]";
    @FindBy(xpath = MODA_360_PROGRAMS_XPATH)
    private WebElement moda360Heading;

    @FindBy(xpath = "(//vlocity_ins-block[contains(@data-conditions, 'FirstProgramId')])[1]")
    private WebElement firstProgram;

    private static final String FIRST_PROGRAM_HEADING_XPATH = "(//div[@class='vloc-min-height' and @vlocity_ins-outputfield_outputfield=''])[4]";
    @FindBy(xpath = FIRST_PROGRAM_HEADING_XPATH)
    private WebElement firstProgramHeading;

    private static final String HEALTH_COACHING_PROGRAM_HEADING_XPATH = "//div[contains(text(),'Health coaching program')]";
    @FindBy(xpath = HEALTH_COACHING_PROGRAM_HEADING_XPATH)
    private WebElement healthCoachingProgramHeading;

    @FindBy(xpath = "//span[text()='Moda 360 Programs']/preceding-sibling::lightning-icon")
    private WebElement backModa360Programs;

    @FindBy(xpath = "//div[contains(text(),'Get extra dental benefits')]")
    private WebElement getExtraBenefits;

    @FindBy(xpath = "//span[contains(text(),'Health through Oral Wellness (PDF)')][1]")
    private List<WebElement> healthThroughOralWellNess;
    
    @FindBy(xpath = "(//vlocity_ins-output-field[contains(@data-conditions, 'PDF')])[1]")
    private WebElement pdfLink;

    @FindBy(xpath = "//div/span[contains(text(),'Leave')]")
    private WebElement leavePopupButton;

    @FindBy(xpath = "//span[@title='Cancel']")
    private WebElement cancelButton;

    @Step("360 programs displayed")
    public void visibility360ProgramsHeading(){
        isElementDisplayed(driver, 40,"360 programs displayed", MODA_360_PROGRAMS_XPATH);
    }

    @Step("Click on first program")
    public void clickOnFirstProgram() throws InterruptedException {
        Scroll.scrollToElement(driver,firstProgram);
        click(firstProgram,"Click on first program");
    }

    @Step("Check first program heading")
    public void checkFirstProgramHeading() {
        isElementDisplayed(driver,40,"Check first program heading", FIRST_PROGRAM_HEADING_XPATH);
    }

    @Step("Check health coaching program heading")
    public boolean checkHealthCoachingProgramHeading() {
        return isElementDisplayed(driver,40,"Check health coaching program heading", HEALTH_COACHING_PROGRAM_HEADING_XPATH);
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

    @Step("Click on PDF link of the program details page")
    public void clickOnPDFLink() throws InterruptedException {
        Scroll.scrollToElement(driver,pdfLink);
        click(pdfLink,"Click on PDF link of the program details page");
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
    public String getPDFContent(String url) {
        return readPDFContent(url,"Get the PDF content");
    }

}
