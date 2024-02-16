package com.moda.pages;

import com.moda.core.Constants;
import com.moda.utils.AllureReporterHelper;
import com.moda.utils.ExplicitWait;
import com.moda.utils.ScreenShot;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name='loginBtn']")
    private WebElement loginButton;

    @FindBy(xpath = "//li[contains(text(),'Sorry, that password was incorrect. Remember, your')]")
    private WebElement errorMessageText;

    public void inputUserName(String userName){
        Allure.step("Enter user name");
        Allure.parameter("userName", userName);
        userNameField.sendKeys(userName);
        ScreenShot screenShot = new ScreenShot(driver);
        String inputSc =  screenShot.takeScreenshot(userName);
        AllureReporterHelper.attachScreenshot(inputSc);
        logger.info("Inputting username: {}", userName); // Log the username input action
    }

    public void inputPassword(String password){
        Allure.step("Enter Password");
        Allure.parameter("password", password);

        passwordField.sendKeys(password);
        logger.info("Inputting password: {}", password);
    }

    public void submitLogin(){
        Allure.step("Submit Login");
        loginButton.submit();
        logger.info("Login Button Clicked");
    }

    public String loginErrorMessage(){
        Allure.step("Login Error Message");
        String fullErrorMessage = errorMessageText.getText();

        // Extract only the Error Text
        String errorMessage = fullErrorMessage.split("\\n")[0].trim();
        logger.info("Error Messafe: {}", errorMessage);
        return errorMessage;
    }




}
