package com.moda.testcase;

import com.moda.testcase.basetc.Base;
import com.moda.core.Constants;
import com.moda.core.ResourceString;
import com.moda.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTestCase extends Base {
    String userName = Constants.USER;
    String wrongPassword = Constants.WRONG_PASSWORD;

    @Test(priority = 1, retryAnalyzer = Retry.class)
    @Epic("Moda Main Web App")
    @Feature("Authentication")
    @Story("Authentication with Invalid data")
    @Severity(SeverityLevel.NORMAL)
    @Description("Invalid Credentials Test")
    public void inValidLoginTest() {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.inputUserName(userName);
        loginPage.inputPassword(wrongPassword);
        loginPage.submitLogin();

        String errorMessage = loginPage.loginErrorMessage();
        Assert.assertEquals(errorMessage, ResourceString.LOGIN_ERROR_TEXT);
    }


}
