package com.moda.listeners;

import com.moda.basetc.Base;
import com.moda.utils.AllureReport;
import com.moda.utils.ExtraWaiting;
import com.moda.utils.LogHelper;
import com.moda.utils.ScreenShot;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class TestListener implements ITestListener {
    String videoRecordingFileName = "";
    public WebDriver getDriver() {
        return Base.getDriver();
    }

    public void onTestStart(ITestResult result){
        String testMethod = result.getMethod().getMethodName();
        LogHelper.getLogger().info("Starting test: " + testMethod);

//        waitForPageLoad(Base.getDriver());
//        DriverManagerType wdmType = Base.getWDM().getDriverManagerType();
//
//        if ( wdmType.toString().equals("EDGE") || wdmType.toString().equals("CHROME") ){
//            videoRecordingFileName = testMethod;
//            deleteIfExists(videoRecordingFileName);
//            Base.getWDM().startRecording(getDriver(), videoRecordingFileName);
//        }

    }

    public void onTestSuccess(ITestResult result) {
//        DriverManagerType wdmType = Base.getWDM().getDriverManagerType();
//
//        if ( wdmType.toString().equals("EDGE") || wdmType.toString().equals("CHROME") ){
//            Base.getWDM().stopRecording(getDriver());
//            try {
//                saveRecording(videoRecordingFileName);
//                AllureReport.attachVideoWebm(result.getMethod().getMethodName(), getRecFile(videoRecordingFileName).toString());
//                deleteIfExists(videoRecordingFileName);
//            } catch (InterruptedException | IOException e) {
//                LogHelper.getLogger().error(e.getMessage());
//            }
//        }
    }

    public void onTestFailure(ITestResult result){

        String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error";
        LogHelper.getLogger().error("Test failed: " + result.getMethod().getMethodName() + " - Error: " + errorMessage);

        ScreenShot screenShot = new ScreenShot(getDriver());
        String inputSc =  screenShot.takeScreenshot(result.getMethod().getMethodName());
        AllureReport.attachScreenshot(inputSc,result.getMethod().getMethodName());

//        DriverManagerType wdmType = Base.getWDM().getDriverManagerType();
//
//        if ( wdmType.toString().equals("EDGE") || wdmType.toString().equals("CHROME") ){
//            Base.getWDM().stopRecording(getDriver());
//            try {
//                saveRecording(videoRecordingFileName);
//                AllureReport.attachVideoWebm(result.getMethod().getMethodName(), getRecFile(videoRecordingFileName).toString());
//                deleteIfExists(videoRecordingFileName);
//            } catch (InterruptedException | IOException e) {
//                LogHelper.getLogger().error(e.getMessage());
//            }
//        }
    }

    private void waitForPageLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        ExpectedCondition<Boolean> pageLoadCondition = wd -> {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) wd;
            String readyState = (String) jsExecutor.executeScript("return document.readyState");
            return "complete".equals(readyState);
        };
        wait.until(pageLoadCondition);
    }

    private void saveRecording(String fileName) throws InterruptedException {
        File recFile = getRecFile(fileName);
        while (!Files.exists(recFile.toPath())) {
            ExtraWaiting.extraWait(3);
        }
    }

    public void deleteIfExists(String fileName) {
        File recFile = getRecFile(fileName);
        if (recFile.exists()) {
            recFile.delete();
        }
    }

    private File getRecFile(String fileName) {
        String REC_EXT = ".webm";
        File targetFolder = new File(System.getProperty("user.home"), "Downloads");
        File recFile = new File(targetFolder, fileName + REC_EXT);
        return recFile;
    }


}
