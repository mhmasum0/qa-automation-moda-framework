package com.moda;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import com.moda.basetc.BaseTest;
import com.moda.core.Constants;
import com.moda.core.ResourceString;
import com.moda.pages.DashboardPage;
import com.moda.pages.LoginPage;
import com.moda.utils.AllureReport;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UserEligibilityTC extends BaseTest {
    String userName;
    String password;
    String appURL;

    @Test(dataProvider = "dataProviderFromExcel")
    public void userEligibilityTest(String group, String pcb360, String bh360, String userID){
        appURL = Constants.URL;
        userName = userID;
        password = Constants.PASSWORD;
        getDriver().get(appURL);

        LoginPage loginPage = new LoginPage(getDriver());

        Response response = loginPage.loginUserAPI(userName, password);

        // Check the status code
        AllureReport.step("API status code: "+ response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        // Check the response content type
        AllureReport.step("API content type:" + response.getContentType() );
        Assert.assertEquals(response.getContentType(), "text/plain;charset=UTF-8", "Response type is not text/plain;charset=UTF-8");

        loginPage.inputUserName(userName);
        loginPage.inputPassword(password);
        loginPage.submitLogin();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        String welcomeMessage = dashboardPage.welcomeMessage();

        Assert.assertTrue(welcomeMessage.contains(ResourceString.WELCOME_MESSAGE), ResourceString.WELCOME_MESSAGE + " does not exist in the" + welcomeMessage);

        Response pcpEligibilityResponse = dashboardPage.pcpEligibilityWithAPI();

        // Check the status code
        AllureReport.step("PCP Eligibility API status code: "+ pcpEligibilityResponse.getStatusCode());
        Assert.assertEquals(pcpEligibilityResponse.getStatusCode(), 200, "Status code is not 200");

        // Check the response content type
        AllureReport.step("PCP Eligibility API content type:" + pcpEligibilityResponse.getContentType() );
        Assert.assertEquals(pcpEligibilityResponse.getContentType(), "application/json", "Response type is not application/json");

        // Fetch the full response and print it
        AllureReport.step("PCP Eligibility API response: " + pcpEligibilityResponse.getBody().asString());

        // Fetch the pcpType from the response
         String pcpType = pcpEligibilityResponse.jsonPath().getString("pcpType");
       // Fetch the groupName from response
        String groupName = pcpEligibilityResponse.jsonPath().getString("groupName");
        System.out.println("PCP Typ: " + pcpType);
        System.out.println("PCP Group: " + groupName);

    }

    @DataProvider(name = "dataProviderFromExcel")
    public Object[][] getDataFromExcel() throws IOException {
        String useCase4Excel = Paths.get(System.getProperty("user.dir"),"src", "test", "resources", "usecase4-data.xlsx").toString();
        String sheetName = "usercase4";
        FileInputStream inputStream = new FileInputStream(new File(useCase4Excel));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Object[][] data = new Object[rowCount][4]; // Assuming 4 columns in Excel

        for (int i = 1; i <= rowCount; i++) { // Starting from 1 to skip header row
            Row row = sheet.getRow(i);
            for (int j = 0; j < 4; j++) { // Assuming 4 columns in Excel
                data[i - 1][j] = row.getCell(j).toString();
            }
        }
        workbook.close();
        return data;
    }
}
