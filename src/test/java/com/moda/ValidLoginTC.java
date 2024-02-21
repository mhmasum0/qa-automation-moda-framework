package com.moda;

import com.moda.basetc.BaseTest;
import com.moda.core.Constants;
import com.moda.core.ResourceString;
import com.moda.pages.DashboardPage;
import com.moda.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidLoginTC extends BaseTest {

    String userName = Constants.USER;
    String password = Constants.PASSWORD;

    @Test(priority = 1, description = "Valid Credential login")
    public void ValidLoginTest(){
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.inputUserName(userName);
        loginPage.inputPassword(password);
        loginPage.submitLogin();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        String welcomeMessage = dashboardPage.welcomeMessage();

        Assert.assertTrue(welcomeMessage.contains(ResourceString.WELCOME_MESSAGE), ResourceString.WELCOME_MESSAGE + " does not exist in the" + welcomeMessage);
    }
}
