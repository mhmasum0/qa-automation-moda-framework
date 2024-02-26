package com.moda;

import com.moda.basetc.BaseTest;
import com.moda.core.ResourceString;
import com.moda.pages.DashboardPage;
import com.moda.utils.ExtraWating;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Moda360ProgramsTC extends BaseTest {
  // uat environment
  
  @Test(dependsOnMethods = "com.moda.ValidLoginTC.ValidLoginTest")
  public void moda_360_programs_page() throws Exception {
    DashboardPage dashboardPage = new DashboardPage(getDriver());

    dashboardPage.clickOnModa360Menu();
    dashboardPage.clickModa360ProgramMenu();
    dashboardPage.visibility_360_programs_heading();

    dashboardPage.clickOnHealthCoachingProgram();
    ExtraWating.extraWait(3);
    dashboardPage.checkHealthCoachingProgramHeading();

    dashboardPage.clickOnBackModa360Programs();
    dashboardPage.clickOnGetExtraBenifits();
    ExtraWating.extraWait(3);
    dashboardPage.clickOnHealthThroughOraWellness();
    ExtraWating.extraWait(3);
    dashboardPage.clickOnCancel();
    ExtraWating.extraWait(3);
    dashboardPage.clickOnHealthThroughOraWellness();
    ExtraWating.extraWait(3);
    dashboardPage.clickLeavePopup();

    String originalTab = getDriver().getWindowHandle();
    dashboardPage.goToNextTab(originalTab);

    String pdfURL = getDriver().getCurrentUrl();
    final String pdfContent = dashboardPage.getPDFContent(pdfURL);

    Assert.assertTrue(pdfContent.contains(ResourceString.ORAL_HEALTH_PDF_CONTENT));

    dashboardPage.closeTab();
    dashboardPage.backToOriginalTab(originalTab);

    ExtraWating.extraWait(5);
  }

}
