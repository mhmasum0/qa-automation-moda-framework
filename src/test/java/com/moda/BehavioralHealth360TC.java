package com.moda;

import com.moda.basetc.BaseTest;
import com.moda.core.ResourceString;
import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWating;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BehavioralHealth360TC extends BaseTest {
    // uat

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
        String originalTab = getDriver().getWindowHandle();
        dashboardPage.goToNextTab(originalTab);

        String hopeHealthHealing = dashboardPage.getMainHeading();
        Assert.assertEquals(hopeHealthHealing, ResourceString.Hazelden_Betty_Ford);

        dashboardPage.closeTab();
        dashboardPage.backToOriginalTab(originalTab);

        ExtraWating.extraWait(5);
    }
}
