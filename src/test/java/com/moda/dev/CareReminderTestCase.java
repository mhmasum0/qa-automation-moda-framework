package com.moda.dev;

import com.moda.basetc.Base;
import com.moda.core.ResourceString;
import com.moda.pages.CareReminderPage;
import com.moda.pages.DashboardPage;
import com.moda.utils.AllureReport;
import com.moda.utils.ExtraWaiting;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CareReminderTestCase extends Base {

    @Test(dependsOnMethods = "com.moda.ValidLoginTestCase.validLoginTest")
    @Epic("Moda Main Web App")
    @Story("Care reminder validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Care Reminder Validation")
    public void careReminderTest() throws InterruptedException {

        DashboardPage dashboardPage = new DashboardPage(getDriver());

        dashboardPage.clickOnModa360Menu();
        dashboardPage.clickOnCareRemindersMenu();

        CareReminderPage careReminderPage = new CareReminderPage( getDriver() );
        String careRemindersPageHeading = careReminderPage.confirmCareRemindersPage();

        AllureReport.step("Validation: care reminder page");
        Assert.assertEquals(careRemindersPageHeading, ResourceString.CARE_REMINDERS);

        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnActiveButton();

        careReminderPage.checkFirstCareReminder();

        Response responseActiveCareReminder = careReminderPage.activeCareReminderWithAPI();

        AllureReport.step("API: Status Code: " + responseActiveCareReminder.getStatusCode());
        Assert.assertEquals(responseActiveCareReminder.getStatusCode(), 200);
        AllureReport.step("API: content type: " + responseActiveCareReminder.getContentType());

        JsonPath jsonPath = responseActiveCareReminder.jsonPath();
        List<String> titles = jsonPath.getList("title");

        AllureReport.step("API: Reminder count: " + titles.size());
        AllureReport.step("API: available reminder: " + titles.get(0));
        Assert.assertEquals(titles.get(0), ResourceString.getFirstCareReminder());


        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnFirstCareReminder();
        careReminderPage.clickOnSnoozeReminder();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnOK();

        String careRemindersPageHeadingAgain = careReminderPage.confirmCareRemindersPage();
        Assert.assertEquals(careRemindersPageHeadingAgain, ResourceString.CARE_REMINDERS);

        Response responseActiveCareReminderAgain = careReminderPage.activeCareReminderWithAPI();
        JsonPath jsonPathAgain = responseActiveCareReminderAgain.jsonPath();
        List<String> titlesAgain = jsonPathAgain.getList("title");

        AllureReport.step("API: Status Code: " + responseActiveCareReminderAgain.getStatusCode());
        AllureReport.step("API: content type: " + responseActiveCareReminderAgain.getContentType());

        AllureReport.step("API: Reminder count: " + titlesAgain.size());
        AllureReport.step("API: reminder should not be available: " + titlesAgain.get(0));
        Assert.assertNotEquals(titlesAgain.get(0), ResourceString.getFirstCareReminder());

        careReminderPage.clickOnSnoozedButton();

        String confirmSnoozed = careReminderPage.confirmYearlyDentalExamAndCleaningSnoozed();
        Assert.assertEquals(confirmSnoozed, ResourceString.getFirstCareReminder());

        careReminderPage.clickOnYearlyDentalExamAndCleaningSnoozed();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnUnsnoozeCareReminder();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnUnsnoozeButton();

        Response responseActiveCareReminderUnsnoozed = careReminderPage.activeCareReminderWithAPI();
        JsonPath jsonPathUnsnoozed = responseActiveCareReminderUnsnoozed.jsonPath();

        AllureReport.step("API: status code: " + responseActiveCareReminderUnsnoozed.getStatusCode());
        Assert.assertEquals( responseActiveCareReminderUnsnoozed.getStatusCode(), 200);
        List<String> titlesUnsnoozed = jsonPathUnsnoozed.getList("title");

        AllureReport.step("API: reminder count: " + titlesUnsnoozed.size());
        AllureReport.step("API: available reminder: " + titlesUnsnoozed.get(0));
        Assert.assertEquals(titlesUnsnoozed.get(0), ResourceString.getFirstCareReminder());

        ExtraWaiting.extraWait(7);
    }

}
