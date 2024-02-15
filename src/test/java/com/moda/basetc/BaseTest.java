package com.moda.basetc;

import com.moda.core.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    // initialize web driver
    public WebDriver driver;
    String URL = Constants.URL;

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser){

        switch (browser.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Browser \"" + browser + "\" isn't supported.");
        }

//        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterClass(alwaysRun = true)
    public void wrapUp() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
