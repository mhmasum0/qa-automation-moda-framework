package com.mahmud;

import com.mahmud.basetc.BaseTest;
import com.mahmud.core.Constants;
import com.mahmud.core.ResourceString;
import com.mahmud.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTC extends BaseTest {
    String userName = Constants.USER;
    String wrongPassword = Constants.WRONG_PASSWORD;

    @Test(priority = 1, description = "Invalid Credentials Test")
    public void inValidLoginTest(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(userName);
        loginPage.inputPassword(wrongPassword);
        loginPage.submitLogin();

        String errorMessage = loginPage.loginErrorMessage();
        Assert.assertEquals(errorMessage, ResourceString.LOGIN_ERROR_TEXT);
    }


}
