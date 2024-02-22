package com.moda;

import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWating;
import org.testng.annotations.Test;

public class BehavioralHealth360TC extends ValidLoginTC {

    @Test(dependsOnMethods = "ValidLoginTest")
    public void behavioralHealth360() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.click360Menu();
        dashboardPage.clickModa360ProgramMenu();
        dashboardPage.visibility_360_programs_heading();

        dashboardPage.clickBehavioral_health_360();
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
