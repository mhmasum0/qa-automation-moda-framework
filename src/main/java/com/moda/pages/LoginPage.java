package com.moda.pages;

import com.moda.api.LoginEndPoints;
import com.moda.api.models.User;
import com.moda.core.ShareData;
import com.moda.pages.base.BasePage;
import com.moda.utils.LogHelper;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Allure;
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

    @FindBy(xpath = "//li[contains(text(),'Sorry, that password was incorrect. Remember, your')]")
    private WebElement errorMessageText;

    @Step("Input Login Username")
    public void inputUserName(String userName){
        input(userNameField,userName, "Enter User name");
    }

    @Step("Input login password")
    public void inputPassword(String password){
        input(passwordField,password, "Enter password");
    }

    @Step("Click on submit")
    public void submitLogin(){
        click(loginButton, "Click on login");
    }

    @Step("Login error message")
    public String loginErrorMessage(){
        String fullErrorMessage = errorMessageText.getText();

        // Extract only the Error Text
        String errorMessage = fullErrorMessage.split("\\n")[0].trim();
        LogHelper.getLogger().info("Error Message: {}", errorMessage);
        return errorMessage;
    }

    @Step("Login API")
    public Response loginUserAPI(String userName, String password){
        // Login user API
        User userPayload = new User(userName, password);
        Response response = LoginEndPoints.loginUsers(userPayload);
        ShareData.accessToken = String.valueOf(response.body().print());
        LogHelper.getLogger().info("Status Code: " + response.getStatusCode());
        LogHelper.getLogger().info("Body: " + response.body().print());
        return response;
    }


}
