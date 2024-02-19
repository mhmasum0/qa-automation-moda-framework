package com.moda.listeners;

import com.moda.basetc.BaseTest;
import com.moda.utils.AllureReporterHelper;
import com.moda.utils.ScreenShot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public WebDriver getDriver() {
        // Return the WebDriver instance
        return BaseTest.getDriver();
    }
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    public void onTestStart(ITestResult result){
        String description = result.getMethod().getDescription();
        if (description != null) {
            description = description.replace(" ", "-");
            logger.info("Starting test: " + result.getMethod().getMethodName() + " - " + description);
        } else {
            logger.info("Starting test: " + result.getMethod().getMethodName());
        }
    }

    public void onTestFailure(ITestResult result){
        String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error";

        // Log only the error message
        logger.error("Test failed: " + result.getMethod().getMethodName() + " - Error: " + errorMessage);
        ScreenShot screenShot = new ScreenShot(getDriver());
        String inputSc =  screenShot.takeScreenshot(result.getMethod().getMethodName());
        AllureReporterHelper.attachScreenshot(inputSc,result.getMethod().getMethodName());

    }


}
