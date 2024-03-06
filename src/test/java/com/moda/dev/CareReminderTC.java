package com.moda.dev;

import com.moda.basetc.BaseTest;
import com.moda.core.ResourceString;
import com.moda.core.ShareData;
import com.moda.pages.CareReminderPage;
import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWaiting;

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

        Assert.assertEquals(careRemindersPageHeading, ResourceString.CARE_REMINDERS);

        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnActiveButton();

        Response responseActiveCareReminder = careReminderPage.activeCareReminderWithAPI();

        JsonPath jsonPath = responseActiveCareReminder.jsonPath();
        List<String> titles = jsonPath.getList("title");

        // Assert
        Assert.assertEquals(titles.size(), 2);
        Assert.assertEquals(titles.get(0), ResourceString.YEARLY_DENTAL_EXAM_AND_CLEANING_SNOOZED);


        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnYearlyDentalReminderLink();
        careReminderPage.clickOnSnoozeReminder();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnOK();

        String careRemindersPageHeadingAgain = careReminderPage.confirmCareRemindersPage();
        Assert.assertEquals(careRemindersPageHeadingAgain, ResourceString.CARE_REMINDERS);

        // validate that the snoozed care reminder is not in the active care reminder list
        Response responseActiveCareReminderAgain = careReminderPage.activeCareReminderWithAPI();
        JsonPath jsonPathAgain = responseActiveCareReminderAgain.jsonPath();
        List<String> titlesAgain = jsonPathAgain.getList("title");

        // Assert that title size is 1
        Assert.assertEquals(titlesAgain.size(), 1);
        Assert.assertNotEquals(titlesAgain.get(0), ResourceString.YEARLY_DENTAL_EXAM_AND_CLEANING_SNOOZED);

        careReminderPage.clickOnSnoozedButton();

        String confirmSnoozed = careReminderPage.confirmYearlyDentalExamAndCleaningSnoozed();
        Assert.assertEquals(confirmSnoozed, ResourceString.YEARLY_DENTAL_EXAM_AND_CLEANING_SNOOZED);

        careReminderPage.clickOnYearlyDentalExamAndCleaningSnoozed();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnUnsnoozeCareReminder();
        ExtraWaiting.extraWait(3);
        careReminderPage.clickOnUnsnoozeButton();

        // validate that the unsnoozed care reminder is in the active care reminder list
        Response responseActiveCareReminderUnsnoozed = careReminderPage.activeCareReminderWithAPI();
        JsonPath jsonPathUnsnoozed = responseActiveCareReminderUnsnoozed.jsonPath();
        List<String> titlesUnsnoozed = jsonPathUnsnoozed.getList("title");

        // Assert that title size is 2
        Assert.assertEquals(titlesUnsnoozed.size(), 2);
        Assert.assertEquals(titlesUnsnoozed.get(0), ResourceString.YEARLY_DENTAL_EXAM_AND_CLEANING_SNOOZED);

        ExtraWaiting.extraWait(7);
    }

}
