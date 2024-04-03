package com.moda;

import com.moda.basetc.Base;
import com.moda.core.Constants;
import com.moda.core.ResourceString;
import com.moda.pages.DashboardPage;
import com.moda.pages.LoginPage;

import com.moda.utils.AllureReport;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ValidLoginTC extends Base {

    String userName = Constants.USER;
    String password = Constants.PASSWORD;
    String appURL;

    @Test
    @Epic("Moda Main Web App")
    @Feature("Authentication")
    @Story("Authentication with valid login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Valid Credential login")
    public void ValidLoginTest(){
        appURL = Constants.URL;

        LoginPage loginPage = new LoginPage(getDriver());

        Response response = loginPage.loginUserAPI(userName, password);

        AllureReport.step("API status code: "+ response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        AllureReport.step("API content type:" + response.getContentType() );
        Assert.assertEquals(response.getContentType(), "text/plain;charset=UTF-8", "Response type is not text/plain;charset=UTF-8");

        loginPage.inputUserName(userName);
        loginPage.inputPassword(password);
        loginPage.submitLogin();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        String welcomeMessage = dashboardPage.welcomeMessage();

        Assert.assertTrue(welcomeMessage.contains(ResourceString.WELCOME_MESSAGE), ResourceString.WELCOME_MESSAGE + " does not exist in the" + welcomeMessage);
    }
}
