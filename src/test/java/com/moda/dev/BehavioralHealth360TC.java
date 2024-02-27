package com.moda.dev;

import com.moda.basetc.BaseTest;
import com.moda.core.ResourceString;
import com.moda.pages.BH360ProgramsPage;
import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWaiting;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BehavioralHealth360TC extends BaseTest {

    @Test(dependsOnMethods = "com.moda.ValidLoginTC.ValidLoginTest")
    public void behavioralHealth360() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.clickOnModa360Menu();
        dashboardPage.clickOnBehavioralHealth360Menu();

        ExtraWaiting.extraWait(3);

        BH360ProgramsPage bh360ProgramsPage = new BH360ProgramsPage(getDriver());

        bh360ProgramsPage.clickAddictionCare();

        ExtraWaiting.extraWait(3);
        bh360ProgramsPage.clickLearnMore();
        ExtraWaiting.extraWait(3);
        dashboardPage.clickOnCancel();
        ExtraWaiting.extraWait(3);
        bh360ProgramsPage.clickLearnMore();
        ExtraWaiting.extraWait(3);

        dashboardPage.clickLeavePopup();
        String originalTab = getDriver().getWindowHandle();
        dashboardPage.goToNextTab(originalTab);

        String hopeHealthHealing = dashboardPage.getMainHeading();
        Assert.assertEquals(hopeHealthHealing, ResourceString.Hazelden_Betty_Ford);

        dashboardPage.closeTab();
        dashboardPage.backToOriginalTab(originalTab);

        ExtraWaiting.extraWait(5);
    }
}
