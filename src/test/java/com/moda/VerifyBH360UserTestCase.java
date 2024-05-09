package com.moda;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.moda.basetc.Base;
import com.moda.core.Constants;
import com.moda.core.ResourceString;
import com.moda.pages.BH360ProgramsPage;
import com.moda.pages.DashboardPage;
import com.moda.pages.LoginPage;
import com.moda.utils.AllureReport;
import com.moda.utils.DataFromExcel;

import com.moda.utils.ExtraWaiting;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class VerifyBH360UserTestCase extends Base {
    String userName;
    String password;
    String appURL;

    @Test(dataProvider = "dataProviderFromExcel")
    @Epic("Moda Main Web App")
    @Feature("BH360 user test ")
    @Story("Verify BH360 users")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify BH360 users")
    public void verifyBH360UserTest(String userID, String isBH360Programs) throws InterruptedException {
        appURL = Constants.URL;
        userName = userID;
        password = Constants.PASSWORD;

        if ( !getDriver().getCurrentUrl().equals(appURL)){
            getDriver().get(appURL);
        }

        LoginPage loginPage = new LoginPage(getDriver());

        Response response = loginPage.loginUserAPI(userName, password);

        AllureReport.step("API status code: "+ response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        AllureReport.step("API content type:" + response.getContentType() );
        Assert.assertEquals(response.getContentType(), "text/plain;charset=UTF-8", "Response type is not text/plain;charset=UTF-8");

        Response getAccountResponse = loginPage.getAccountsWithAPI();

        AllureReport.step("Get Accounts with API: firstName => " + getAccountResponse.jsonPath().getString("members[0].firstName"));
        softAssert.assertTrue(getAccountResponse.getBody().print().contains("firstName"));

        loginPage.inputUserName(userName);
        loginPage.inputPassword(password);
        loginPage.submitLogin();

        boolean isTermsAndPoliciesDisplayed = loginPage.waitForTermsAndPolicies();
        if(isTermsAndPoliciesDisplayed){
            loginPage.processTestForm(Constants.TEST_EMAIL);
        }

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        String welcomeMessage = dashboardPage.welcomeMessage();

        Assert.assertTrue(welcomeMessage.contains(ResourceString.WELCOME_MESSAGE), ResourceString.WELCOME_MESSAGE + " does not exist in the" + welcomeMessage);

        JsonPath jsonPath = getAccountResponse.jsonPath();

        String firstName = jsonPath.getString("members[0].firstName");
        String lastName = jsonPath.getString("members[0].lastName");
        String fullNameFromAPI = firstName + lastName;
        String memberName = dashboardPage.getMemberName();
        softAssert.assertEquals(fullNameFromAPI, memberName);

        String groupNameFromAPI = jsonPath.getString("groupName");
        String groupName = dashboardPage.getGroupName();
        Allure.parameter("groupName", groupName);
        softAssert.assertEquals(groupNameFromAPI, groupName);

        boolean isBH360GroupIconDisplayed = dashboardPage.isBH360GroupIconDisplayed();
        softAssert.assertTrue(isBH360GroupIconDisplayed);

        boolean isBH360TitleDisplayed = dashboardPage.isBH360TitleDisplayed();
        softAssert.assertTrue(isBH360TitleDisplayed);

        dashboardPage.clickBH360Menu();
        dashboardPage.clickOnBehavioralHealth360Menu();

        BH360ProgramsPage bh360ProgramsPage = new BH360ProgramsPage(getDriver());
        bh360ProgramsPage.isBH360LateralDisplayed();
        ExtraWaiting.extraWait(3);

        List<String> programList = bh360ProgramsPage.programList();
        List<String> programListFromResource = Arrays.asList(ResourceString.getBH360ProgramList());

        AllureReport.step("Check Program list: " + programList);
        softAssert.assertEquals(programList, programListFromResource);

        if (isBH360Programs.equals("Y")){
            bh360ProgramsPage.clickProgramList();
        }

    }

    @DataProvider(name = "dataProviderFromExcel")
    public Object[][] getDataFromExcel() {
        String useCase6Excel = Paths.get(System.getProperty("user.dir"),"src", "test", "resources", "test-data","usecase6-data.xlsx").toString();
        String sheetName = "usecase6";
        int columnCount = 2;

        return DataFromExcel.getDataFromExcel(useCase6Excel, sheetName, columnCount);
    }
}
