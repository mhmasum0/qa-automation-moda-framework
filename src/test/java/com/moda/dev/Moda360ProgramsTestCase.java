package com.moda.dev;

import com.moda.basetc.Base;
import com.moda.pages.DashboardPage;
import com.moda.pages.Moda360ProgramsPage;
import com.moda.utils.ExtraWaiting;
import org.testng.annotations.Test;

public class Moda360ProgramsTestCase extends Base {

    @Test(dependsOnMethods = "com.moda.ValidLoginTC.ValidLoginTest")
    public void moda_360_programs_page() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        Moda360ProgramsPage moda360ProgramsPage = new Moda360ProgramsPage(getDriver());

        dashboardPage.clickOnModa360Menu();
        dashboardPage.clickModa360ProgramMenu();

        moda360ProgramsPage.visibility360ProgramsHeading();

        ExtraWaiting.extraWait(5);
    }
}
