package com.moda.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.Reporter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureReport {

    private AllureReport(){
        throw new IllegalStateException("This is utility class");
    }

    public static void attachScreenshot(String screenshotFilePath,String fileName) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(screenshotFilePath));
            Allure.addAttachment(fileName, "image/png", new ByteArrayInputStream(bytes), "png");
        } catch (IOException e) {
            LogHelper.getLogger().error("Exception while reading the screenshot file: {}", e.getMessage());
        }
    }

    @Attachment(value = "video-{0}", type = "video/webm", fileExtension = ".webm")
    public static byte[] attachVideoWebm(String name,String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    @Step("{0}")
    public static void step(String description){
        LogHelper.getLogger().info(description);
        Reporter.log(description);
    }
}
