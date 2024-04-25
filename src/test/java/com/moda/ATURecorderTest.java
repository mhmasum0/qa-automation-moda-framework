package com.moda;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ATURecorderTest {
    static WebDriver driver;
    static ATUTestRecorder recorder;
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    static Date date = new Date();
    static String videoName = "TC_ATU_Test_Video" + df.format(date);

    @Test
    public void testATURecorder() throws Exception {
        String videoDir = getFilePath();
        recorder = new ATUTestRecorder(videoDir,videoName,false);
        recorder.start();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.yatra.com/");
        Thread.sleep(2000);

        driver.quit();
        recorder.stop();
    }

    public String getFilePath()
    {
        File destination = new File("Video-Recordings");
        String filepath = destination.getAbsolutePath();
        if (!destination.exists()){
            destination.mkdirs();
        }
        return filepath;
    }
}
