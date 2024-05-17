package com.moda.testcase;

import com.moda.core.ResourceString;
import com.moda.pages.BH360ProgramsPage;
import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWaiting;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BehavioralHealth360TestCase extends ValidLoginTestCase {

    @Test(retryAnalyzer = Retry.class, groups = "behavioralHealth360", dependsOnGroups = "validLogin")
    @Epic("Moda Main Web App")
    @Feature("Validate the Behavioral 360 program")
    @Story("Behavioral Health 360 Program validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Behavioral 360 program Test")
    public void BehavioralHealth360() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.clickOnModa360Menu();
        dashboardPage.clickOnBehavioralHealth360Menu();

        ExtraWaiting.extraWait(3);

        BH360ProgramsPage bh360ProgramsPage = new BH360ProgramsPage(getDriver());

        bh360ProgramsPage.clickAddictionCare();

        ExtraWaiting.extraWait(3);
        bh360ProgramsPage.clickLearnMore();
        ExtraWaiting.extraWait(3);
        bh360ProgramsPage.clickOnCancel();
        ExtraWaiting.extraWait(3);
        bh360ProgramsPage.clickLearnMore();
        ExtraWaiting.extraWait(3);

        bh360ProgramsPage.clickLeavePopup();
        String originalTab = getDriver().getWindowHandle();
        dashboardPage.goToNextTab(originalTab);

        String hopeHealthHealing = bh360ProgramsPage.getMainHeading();
        Assert.assertEquals(hopeHealthHealing, ResourceString.HAZELDEN_BETTY_FORD);

        dashboardPage.closeTab();
        dashboardPage.backToOriginalTab(originalTab);

        ExtraWaiting.extraWait(5);
    }
}
