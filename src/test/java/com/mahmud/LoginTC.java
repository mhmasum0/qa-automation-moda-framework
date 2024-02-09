package com.mahmud;

import com.mahmud.basetc.BaseTest;
import com.mahmud.core.ResourceString;
import com.mahmud.pages.LoginPage;
import com.mahmud.utils.ConfigFileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTC extends BaseTest {
    String userName = ConfigFileReader.getConfigProperty("username");
    String password = ConfigFileReader.getConfigProperty("password");
    String wrongPassword = ConfigFileReader.getConfigProperty("wrong");

    @Test(priority = 1, description = "Invalid Credentials Test")
    public void inValidLoginTest(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(userName);
        loginPage.inputPassword(wrongPassword);
        loginPage.submitLogin();

        String errorMessage = loginPage.loginErrorMessage();
        Assert.assertEquals(errorMessage, ResourceString.LOGIN_ERROR_TEXT);
    }

    @Test(priority = 2, description = "Valid Credential login")
    public void ValidLoginTest(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(userName);
        loginPage.inputPassword(password);
        loginPage.submitLogin();
    }
}
