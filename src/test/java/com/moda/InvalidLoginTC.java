package com.moda;

import com.moda.basetc.BaseTest;
import com.moda.core.Constants;
import com.moda.core.ResourceString;
import com.moda.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class InvalidLoginTC extends BaseTest {
    String userName = Constants.USER;
    String wrongPassword = Constants.WRONG_PASSWORD;

    @Test(priority = 1, description = "Invalid Credentials Test")
    public void inValidLoginTest() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.inputUserName(userName);
        loginPage.inputPassword(wrongPassword);
        loginPage.submitLogin();

        String errorMessage = loginPage.loginErrorMessage();
        Assert.assertEquals(errorMessage, ResourceString.LOGIN_ERROR_TEXT);
    }


}
