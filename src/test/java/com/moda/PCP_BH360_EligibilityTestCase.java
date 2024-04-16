package com.moda;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import com.moda.basetc.Base;
import com.moda.core.Constants;
import com.moda.core.ResourceString;
import com.moda.pages.BH360ProgramsPage;
import com.moda.pages.DashboardPage;
import com.moda.pages.LoginPage;
import com.moda.pages.PCB360Page;
import com.moda.utils.AllureReport;
import com.moda.utils.Common;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class PCP_BH360_EligibilityTestCase extends Base {
    String userName;
    String password;
    String appURL;

    @Test(dataProvider = "dataProviderFromExcel")
    @Epic("Moda Main Web App")
    @Feature("Eligibility test ")
    @Story("PCP and BH360 Eligibility")
    @Severity(SeverityLevel.CRITICAL)
    @Description("PCP and BH360 Eligibility test")
    public void userEligibilityTest(String group, String pcb360, String bh360, String userID) throws InterruptedException {
        appURL = Constants.URL;
        userName = userID;
        password = Constants.PASSWORD;

                    getDriver().get(appURL);
//
//        if ( !getDriver().getCurrentUrl().equals(appURL)){
//            getDriver().get(appURL);
//        }

        LoginPage loginPage = new LoginPage(getDriver());

        Response response = loginPage.loginUserAPI(userName, password);

        AllureReport.step("API status code: "+ response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        AllureReport.step("API content type:" + response.getContentType() );
        Assert.assertEquals(response.getContentType(), "text/plain;charset=UTF-8", "Response type is not text/plain;charset=UTF-8");

        Response getAccountResponse = loginPage.getAccountsWithAPI();

        AllureReport.step("Get Accounts with API: firstName => " + getAccountResponse.jsonPath().getString("firstName"));
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

        Response pcpEligibilityResponse = dashboardPage.pcpEligibilityWithAPI();

        AllureReport.step("PCP Eligibility API status code: "+ pcpEligibilityResponse.getStatusCode());
        Assert.assertEquals(pcpEligibilityResponse.getStatusCode(), 200, "Status code is not 200");

        AllureReport.step("PCP Eligibility API content type:" + pcpEligibilityResponse.getContentType() );
        Assert.assertEquals(pcpEligibilityResponse.getContentType(), "application/json", "Response type is not application/json");

         if ( pcb360.equals("Y") ) {
            String pcpType = pcpEligibilityResponse.jsonPath().getString("pcpType");
            AllureReport.step("Check the PCP Type: " + pcpType);
            Assert.assertEquals(pcpType, ResourceString.PCP_360_ELIGIBILITY, "PCP Type is not PCP360");

             dashboardPage.clickOnFindCareMenu();
             dashboardPage.clickOnPCP360Menu();

            PCB360Page pcb360Page = new PCB360Page(getDriver());
            boolean isPCB360LateralTabDisplayed = pcb360Page.isPCB360LateralTabDisplayed();
            Assert.assertTrue(isPCB360LateralTabDisplayed, "PCB360 lateral is not displayed");
         }

        if ( bh360.equals("Y") ) {
            dashboardPage.clickOnModa360Menu();
            dashboardPage.clickOnBehavioralHealth360Menu();

            BH360ProgramsPage bh360ProgramsPage = new BH360ProgramsPage(getDriver());

            AllureReport.step("Check if BH360 lateral is displayed");
            boolean isBH360LateralDisplayed = bh360ProgramsPage.isBH360LateralDisplayed();
            Assert.assertTrue(isBH360LateralDisplayed, "BH360 lateral is not displayed");

            AllureReport.step("Check BH360 Groups");
            boolean isBH360Group = Common.arrayContainsElement(ResourceString.BH_360_GROUPS, group);
            Assert.assertTrue(isBH360Group, Arrays.toString(ResourceString.BH_360_GROUPS) + " in BH360 group should exist: " + group);
        } else {
            AllureReport.step("Check WithoutBH360 Groups");
            boolean isWithoutBH360Group = Common.arrayContainsElement(ResourceString.WITHOUT_BH_360_GROUPS, group);
            Assert.assertTrue(isWithoutBH360Group, Arrays.toString(ResourceString.WITHOUT_BH_360_GROUPS) + " in WithoutBH360 group should exist: " + group);
        }

        AllureReport.step("PCP Eligibility API response: " + pcpEligibilityResponse.getBody().asString());


    }

    @DataProvider(name = "dataProviderFromExcel")
    public Object[][] getDataFromExcel() throws IOException {
        String useCase4Excel = Paths.get(System.getProperty("user.dir"),"src", "test", "resources", "test-data","usecase4-data.xlsx").toString();
        String sheetName = "usercase4";

        FileInputStream inputStream = new FileInputStream(new File(useCase4Excel));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Object[][] data = new Object[rowCount][4];

        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < 4; j++) {
                data[i - 1][j] = row.getCell(j).toString();
            }
        }
        workbook.close();
        return data;
    }
}
