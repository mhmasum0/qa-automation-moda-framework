package com.moda.pages;

import com.moda.api.LoginEndPoints;
import com.moda.api.models.User;
import com.moda.core.ShareData;
import com.moda.pages.base.BasePage;
import com.moda.utils.LogHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class LoginPage extends BasePage {
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

    private static final String termsAndPoliciesHeaderXpath = "//h1[text()='Terms and policies']";

    @FindBy(xpath = "//input[@id='commPolicy']")
    private WebElement commPolicyCheckbox;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='retypeEmail']")
    private WebElement retypeEmailField;

    @FindBy(xpath = "//input[@name='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//li[contains(text(),'Sorry, that password was incorrect. Remember, your')]")
    private WebElement errorMessageText;

    @Step("Input Login Username")
    public void inputUserName(String userName){
        input(userNameField,userName);
    }

    @Step("Input login password")
    public void inputPassword(String password){
        input(passwordField,password);
    }

    @Step("Click on submit")
    public void submitLogin(){
        click(loginButton, "Click on login");
    }

    @Step("Login error message")
    public String loginErrorMessage(){
        String fullErrorMessage = errorMessageText.getText();

        String errorMessage = fullErrorMessage.split("\\n")[0].trim();
        LogHelper.getLogger().info("Error Message: {}", errorMessage);
        return errorMessage;
    }

    @Step("Login API")
    public Response loginUserAPI(String userName, String password){
        User userPayload = new User(userName, password);
        Response response = LoginEndPoints.loginUsers(userPayload);
        ShareData.setAccessToken(String.valueOf(response.body().print()));
        return response;
    }

    @Step("Get Accounts with API")
    public Response getAccountsWithAPI(){
        String token = ShareData.getAccessToken();
        return LoginEndPoints.getAccount(token);
    }

    @Step("Wait for terms and policies to load")
    public boolean waitForTermsAndPolicies(){
        return isElementDisplayed(driver, 5, "Wait for terms and policies to load", termsAndPoliciesHeaderXpath);
    }

    @Step("Process test form")
    public void processTestForm(String email){
        click(commPolicyCheckbox, "Click on comm policy checkbox");
        input(emailField, email);
        input(retypeEmailField, email);
        click(submitButton, "Click on submit");
    }


}
