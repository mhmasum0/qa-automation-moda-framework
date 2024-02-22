package com.moda.pages;

import com.moda.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Allure;

public class LoginPage extends BasePage {
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
        input(userNameField,userName,logger,"Enter User name");
    }

    public void inputPassword(String password){
        input(passwordField,password,logger,"Enter password");
    }

    public void submitLogin(){
        click(loginButton,logger,"Click on login");
    }

    public String loginErrorMessage(){
        Allure.step("Login Error Message");
        String fullErrorMessage = errorMessageText.getText();

        // Extract only the Error Text
        String errorMessage = fullErrorMessage.split("\\n")[0].trim();
        logger.info("Error Message: {}", errorMessage);
        return errorMessage;
    }




}
