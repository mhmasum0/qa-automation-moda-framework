package com.moda.basetc;

import java.time.Duration;

import com.moda.core.Constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    // initialize web driver
//    public WebDriver driver;
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    String URL = Constants.URL;
    protected static final Logger logger = LogManager.getLogger();

    @Parameters({"browser"})
    @BeforeTest
    public void setUp(@Optional("chrome") String browser) {
        try {
            WebDriver driverInstance;

            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverInstance = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverInstance = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
            }

            driver.set(driverInstance); // Setting the WebDriver in ThreadLocal

            driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driverInstance.get(Constants.URL);

            // Optionally maximize window
             driverInstance.manage().window().maximize();

            logger.info("Navigated to the URL with browser: {}", browser);
        } catch (Exception e) {
            logger.error("Setup failed: {}", e.getMessage());
            throw e; // Rethrow to signal TestNG setup failure
        }
    }

    @AfterTest(alwaysRun = true)
    public void wrapUp() throws InterruptedException {
        Thread.sleep(3000);
        driver.get().quit();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

}
