package com.moda.dev;

import com.moda.basetc.BaseTest;
import com.moda.core.ResourceString;
import com.moda.core.ShareData;
import com.moda.pages.CareReminderPage;
import com.moda.pages.DashboardPage;
import com.moda.utils.AllureReport;
import com.moda.utils.ExtraWaiting;

import io.opentelemetry.sdk.autoconfigure.internal.AutoConfigureUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CareReminderTC extends BaseTest {
    // dev

    @Test(dependsOnMethods = "com.moda.ValidLoginTC.ValidLoginTest")
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

        // Check the status code
        AllureReport.step("API: Status Code: " + responseActiveCareReminder.getStatusCode());
        Assert.assertEquals(responseActiveCareReminder.getStatusCode(), 200);
        AllureReport.step("API: content type: " + responseActiveCareReminder.getContentType());

        JsonPath jsonPath = responseActiveCareReminder.jsonPath();
        List<String> titles = jsonPath.getList("title");

        // Assert
        AllureReport.step("API: Reminder count: " + titles.size());
        AllureReport.step("API: available reminder: " + titles.get(0));
        Assert.assertEquals(titles.get(0), ResourceString.FIRST_CARE_REMINDER);


        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnFirstCareReminder();
//        careReminderPage.clickOnYearlyDentalReminderLink();
        careReminderPage.clickOnSnoozeReminder();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnOK();

        String careRemindersPageHeadingAgain = careReminderPage.confirmCareRemindersPage();
        Assert.assertEquals(careRemindersPageHeadingAgain, ResourceString.CARE_REMINDERS);

        // validate that the snoozed care reminder is not in the active care reminder list
        Response responseActiveCareReminderAgain = careReminderPage.activeCareReminderWithAPI();
        JsonPath jsonPathAgain = responseActiveCareReminderAgain.jsonPath();
        List<String> titlesAgain = jsonPathAgain.getList("title");

        AllureReport.step("API: Status Code: " + responseActiveCareReminderAgain.getStatusCode());
        AllureReport.step("API: content type: " + responseActiveCareReminderAgain.getContentType());
        // Assert that title size is 1
        AllureReport.step("API: Reminder count: " + titlesAgain.size());
        AllureReport.step("API: reminder should not be available: " + titlesAgain.get(0));
        Assert.assertNotEquals(titlesAgain.get(0), ResourceString.FIRST_CARE_REMINDER);

        careReminderPage.clickOnSnoozedButton();

        String confirmSnoozed = careReminderPage.confirmYearlyDentalExamAndCleaningSnoozed();
        Assert.assertEquals(confirmSnoozed, ResourceString.FIRST_CARE_REMINDER);

        careReminderPage.clickOnYearlyDentalExamAndCleaningSnoozed();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnUnsnoozeCareReminder();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnUnsnoozeButton();

        // validate that the unsnoozed care reminder is in the active care reminder list
        Response responseActiveCareReminderUnsnoozed = careReminderPage.activeCareReminderWithAPI();
        JsonPath jsonPathUnsnoozed = responseActiveCareReminderUnsnoozed.jsonPath();

        AllureReport.step("API: status code: " + responseActiveCareReminderUnsnoozed.getStatusCode());
        Assert.assertEquals( responseActiveCareReminderUnsnoozed.getStatusCode(), 200);
        List<String> titlesUnsnoozed = jsonPathUnsnoozed.getList("title");

        // Assert that title size is 2
        AllureReport.step("API: reminder count: " + titlesUnsnoozed.size());
        AllureReport.step("API: available reminder: " + titlesUnsnoozed.get(0));
        Assert.assertEquals(titlesUnsnoozed.get(0), ResourceString.FIRST_CARE_REMINDER);

        ExtraWaiting.extraWait(7);
    }

}
