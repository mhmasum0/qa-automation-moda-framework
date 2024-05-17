package com.moda.testcase;

import com.moda.core.ResourceString;
import com.moda.pages.DashboardPage;
import com.moda.pages.Moda360ProgramsPage;
import com.moda.utils.AllureReport;
import com.moda.utils.ExtraWaiting;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Moda360ProgramsTestCase extends ValidLoginTestCase {

  @Test(retryAnalyzer = Retry.class, groups = "Moda360ProgramsPage", dependsOnGroups = "validLogin")
  @Epic("Moda Main Web App")
  @Feature("Moda 360 program")
  @Story("Moda 360 Program validation")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Moda 360 program")
  public void Moda360ProgramsPage() throws Exception {
    DashboardPage dashboardPage = new DashboardPage(getDriver());

    dashboardPage.clickOnModa360Menu();
    dashboardPage.clickModa360ProgramMenu();

    Moda360ProgramsPage moda360ProgramsPage = new Moda360ProgramsPage(getDriver());

    moda360ProgramsPage.visibility360ProgramsHeading();

    moda360ProgramsPage.clickOnFirstProgram();

    ExtraWaiting.extraWait(3);
    moda360ProgramsPage.checkFirstProgramHeading();

    ExtraWaiting.extraWait(3);
    moda360ProgramsPage.clickOnPDFLink();

    ExtraWaiting.extraWait(3);
    moda360ProgramsPage.clickOnCancel();

    ExtraWaiting.extraWait(3);
    moda360ProgramsPage.clickOnPDFLink();

    ExtraWaiting.extraWait(3);
    moda360ProgramsPage.clickLeavePopup();

    String originalTab = getDriver().getWindowHandle();
    dashboardPage.goToNextTab(originalTab);

    String pdfURL = getDriver().getCurrentUrl();
    final String pdfContent = moda360ProgramsPage.getPDFContent(pdfURL);

    AllureReport.step("Validation: the pdf content");
    Assert.assertTrue(pdfContent.contains(ResourceString.ORAL_HEALTH_PDF_CONTENT));

    dashboardPage.closeTab();
    dashboardPage.backToOriginalTab(originalTab);

    ExtraWaiting.extraWait(5);
  }

}
