package com.moda.dev;

import com.moda.ValidLoginTC;
import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWating;
import org.testng.annotations.Test;

public class Moda360ProgramsTC extends ValidLoginTC {
    @Test(dependsOnMethods = "ValidLoginTest")
    public void moda_360_programs_page() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(getDriver());

        dashboardPage.click360Menu();
        dashboardPage.clickModa360ProgramMenu();
        dashboardPage.visibility_360_programs_heading();

        ExtraWating.extraWait(5);
    }
}
