package com.moda.pages;

import io.qameta.allure.Allure;
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

    @FindBy(name = "userId")
    private WebElement userNameField;

    @FindBy(name = "passwd")
    private WebElement passwordField;

    @FindBy(name = "loginBtn")
    private WebElement loginButton;

    @FindBy(css = "ul.error-msg li")
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
