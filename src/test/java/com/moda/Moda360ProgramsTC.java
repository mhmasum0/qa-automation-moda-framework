package com.moda;

import com.moda.pages.DashboardPage;

import org.testng.annotations.Test;

public class Moda360ProgramsTC extends ValidLoginTC {
  
  @Test(dependsOnMethods = "ValidLoginTest")
  public void moda_360_programs_page() throws InterruptedException {
    DashboardPage dashboardPage = new DashboardPage(getDriver());

    dashboardPage.click360Menu();
    dashboardPage.clickModa360ProgramMenu();
    dashboardPage.visibility_360_programs_heading();
    dashboardPage.clickBehavioral_health_360();

    dashboardPage.clickAddictionCare();
    dashboardPage.clickLearnMore();
    dashboardPage.clickOnCancel();

    dashboardPage.clickLearnMore();
    dashboardPage.clickLeave();

    Thread.sleep(5000);
  }
}
