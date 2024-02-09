package com.mahmud.basetc;

import com.mahmud.utils.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    // initialize web driver
    protected WebDriver driver;
    String URL = ConfigFileReader.getConfigProperty("url");


    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterClass(alwaysRun = true)
    public void wrapUp() throws InterruptedException {
        Thread.sleep(3000);
//        driver.quit();
    }

}
