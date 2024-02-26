package com.moda.dev;

import com.moda.ValidLoginTC;
import com.moda.basetc.BaseTest;
import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWating;

import org.testng.annotations.Test;

public class BehavioralHealth360TC extends BaseTest {

    @Test(dependsOnMethods = "com.moda.ValidLoginTC.ValidLoginTest")
    public void behavioralHealth360() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.clickOnModa360Menu();
        dashboardPage.clickOnBehavioralHealth360Menu();

        ExtraWating.extraWait(3);
        dashboardPage.clickAddictionCare();

        ExtraWating.extraWait(3);
        dashboardPage.clickLearnMore();
        ExtraWating.extraWait(3);
        dashboardPage.clickOnCancel();
        ExtraWating.extraWait(3);
        dashboardPage.clickLearnMore();
        ExtraWating.extraWait(3);

        dashboardPage.clickLeavePopup();
        ExtraWating.extraWait(5);
    }
}
