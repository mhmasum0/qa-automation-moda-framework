package com.moda.utils;

import com.moda.core.Constants;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {
    WebDriver driver;

    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }

    public String takeScreenshot(String name) {
        String screenshotDirectory = Constants.SCREENSHOT_PATH;
        String screenshotName = getScreenshotName(name);
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        try {
            Path path = Paths.get(screenshotDirectory, screenshotName);
            File screenshotFile = path.toFile();

            Files.createDirectories(Paths.get(screenshotFile.getParent()));

            ImageIO.write(screenshot.getImage(), "png", screenshotFile);
            return path.toString();
        } catch (IOException e) {
            LogHelper.getLogger().info(e.getMessage());
        }
        return null;
    }

    private static String getScreenshotName(String name) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        return name + "_" + timestamp + ".png";
    }
}
