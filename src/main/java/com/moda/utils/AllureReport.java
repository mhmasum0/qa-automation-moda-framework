package com.moda.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.Reporter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureReport {
    /**
     * Attach a screenshot to an Allure report.
     *
     * @param screenshotFilePath The path of the screenshot file.
     */
    public static void attachScreenshot(String screenshotFilePath,String fileName) {
        try {
            // Read the screenshot file into a byte array
            byte[] bytes = Files.readAllBytes(Paths.get(screenshotFilePath));

            // Attach the screenshot to the Allure report
            Allure.addAttachment(fileName, "image/png", new ByteArrayInputStream(bytes), "png");

        } catch (IOException e) {
            // Log the error or handle the exception as per your requirements
            LogHelper.getLogger().error("Exception while reading the screenshot file: {}", e.getMessage());
        }
    }

    @Step("{0}")
    public static void step(String description){
        LogHelper.getLogger().info(description);
        Reporter.log(description);
    }
}
