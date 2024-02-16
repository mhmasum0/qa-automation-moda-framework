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
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(userName);
        loginPage.inputPassword(password);
        loginPage.submitLogin();

        DashboardPage dashboardPage = new DashboardPage(driver);
        String welcomeMessage = dashboardPage.welcomeMessage();

        Assert.assertEquals(welcomeMessage, ResourceString.WELCOME_MESSAGE);
    }
}
