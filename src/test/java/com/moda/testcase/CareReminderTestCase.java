package com.moda.testcase;

import java.util.List;

import com.moda.core.ResourceString;
import com.moda.pages.CareReminderPage;
import com.moda.pages.DashboardPage;
import com.moda.utils.AllureReport;
import com.moda.utils.Common;
import com.moda.utils.ExtraWaiting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CareReminderTestCase extends ValidLoginTestCase {

    @Test(retryAnalyzer = Retry.class, groups = "careReminder", dependsOnGroups = "validLogin")
    public void careReminderTest() throws InterruptedException {

        DashboardPage dashboardPage = new DashboardPage(getDriver());

        dashboardPage.clickOnModa360Menu();
        dashboardPage.clickOnCareRemindersMenu();

        CareReminderPage careReminderPage = new CareReminderPage( getDriver() );
        String careRemindersPageHeading = careReminderPage.confirmCareRemindersPage();

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
        String titleFromUI = ResourceString.getFirstCareReminder();

        AllureReport.step("Check Care Reminder from API");
        boolean isTitleExistInAPI = Common.listContainsElement(titles, titleFromUI);
        Assert.assertTrue(isTitleExistInAPI);

        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnFirstCareReminder();

        careReminderPage.clickOnSnoozeReminder();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnOK();


        String careRemindersPageHeadingAgain = careReminderPage.confirmCareRemindersPage();
        Assert.assertEquals(careRemindersPageHeadingAgain, ResourceString.CARE_REMINDERS);

        careReminderPage.clickOnSnoozedButton();
        ExtraWaiting.extraWait(3);
        careReminderPage.checkActiveSnoozed();

        ExtraWaiting.extraWait(3);

        careReminderPage.checkSnoozedCareReminder();
        String snoozedCareReminder = ResourceString.getSnoozedCareReminder();

        Response responseSnoozedCareReminder = careReminderPage.snoozedCareReminderWithAPI();
        List<String> snoozedTitles = responseSnoozedCareReminder.jsonPath().getList("title");

        boolean isTitleExistInAPIAgain = Common.listContainsElement(snoozedTitles, snoozedCareReminder);
        Assert.assertTrue(isTitleExistInAPIAgain);

        careReminderPage.clickOnSnoozedCareReminder();
        ExtraWaiting.extraWait(3);

        careReminderPage.clickOnUnSnoozedCareReminderButton();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnUnsnoozeButton();

        careReminderPage.snoozeReminderHeader();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnCareRemindersBackButton();

        careReminderPage.confirmCareRemindersPage();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnCompletedButton();
        ExtraWaiting.extraWait(5);

        List<String> completedCareReminder = careReminderPage.completedCareReminder();

        Response completedCareReminderResponse = careReminderPage.completedCareReminderWithAPI();
        List<String> completedTitle = completedCareReminderResponse.jsonPath().getList("title");
        AllureReport.step("API: Status Code: " + completedCareReminderResponse.getStatusCode());

        AllureReport.step("Check the completed care reminder from API");
        boolean isTitleExistInAPICompleted = Common.compareStringLists(completedCareReminder, completedTitle);
        Assert.assertTrue(isTitleExistInAPICompleted);

        ExtraWaiting.extraWait(5);
    }

}
