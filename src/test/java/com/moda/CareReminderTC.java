package com.moda;

import com.moda.basetc.Base;
import com.moda.core.ResourceString;
import com.moda.pages.CareReminderPage;
import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWaiting;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CareReminderTC extends Base {

    @Test(dependsOnMethods = "com.moda.ValidLoginTC.ValidLoginTest")
    public void careReminderTest() throws InterruptedException {

        DashboardPage dashboardPage = new DashboardPage(getDriver());

        dashboardPage.clickOnModa360Menu();
        dashboardPage.clickOnCareRemindersMenu();

        CareReminderPage careReminderPage = new CareReminderPage( getDriver() );
        String careRemindersPageHeading = careReminderPage.confirmCareRemindersPage();

        Assert.assertEquals(careRemindersPageHeading, ResourceString.CARE_REMINDERS);

        careReminderPage.clickOnCervicalCancerScreening();

        ExtraWaiting.extraWait(5);
    }

}
