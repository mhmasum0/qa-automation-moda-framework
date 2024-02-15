package com.moda.pages;

import com.moda.core.Constants;
import com.moda.utils.ExplicitWait;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

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
    }

    public void inputPassword(String password){
        Allure.step("Enter Password");
        Allure.parameter("password", password);

        passwordField.sendKeys(password);
    }

    public void submitLogin(){
        Allure.step("Submit Login");
        loginButton.submit();
    }

    public String loginErrorMessage(){
        Allure.step("Login Error Message");
        String fullErrorMessage = errorMessageText.getText();

        // Extract only the Error Text
        String errorMessage = fullErrorMessage.split("\\n")[0].trim();
        return errorMessage;
    }




}
