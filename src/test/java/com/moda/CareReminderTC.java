package com.moda;

import com.moda.basetc.BaseTest;
import com.moda.core.ResourceString;
import com.moda.pages.CareReminderPage;
import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWating;

import org.testng.Assert;
import org.testng.annotations.Test;

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

        careReminderPage.clickOnCervicalCancerScreening();
        careReminderPage.clickOnSnoozeReminder();
        careReminderPage.clickOnOK();

        String careRemindersPageHeadingAgain = careReminderPage.confirmCareRemindersPage();
        Assert.assertEquals(careRemindersPageHeadingAgain, ResourceString.CARE_REMINDERS);

        careReminderPage.clickOnSnoozedCareReminders();
//
//        String confirmSnoozed = careReminderPage.confirmYearlyDentalExamAndCleaningSnoozed();
//        Assert.assertEquals(confirmSnoozed, ResourceString.YEARLY_DENTAL_EXAM_AND_CLEANING_SNOOZED);
//
//        careReminderPage.clickOnYearlyDentalExamAndCleaningSnoozed();
//        careReminderPage.clickOnUnsnoozeCareReminder();
//        careReminderPage.clickOnUnsnoozeButton();

        ExtraWating.extraWait(7);
    }

}
