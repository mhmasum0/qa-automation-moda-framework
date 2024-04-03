package com.moda.basetc;

import java.time.Duration;

import com.moda.core.Constants;
import com.moda.utils.LogHelper;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Base {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<WebDriverManager> wdm = new ThreadLocal<>();

    @Parameters({"browser"})
    @BeforeTest
    public void setUp(@Optional("chrome") String browser) {
        try {
            WebDriver driverInstance;

            switch (browser.toLowerCase()) {
                case "chrome":
                    setWDM(WebDriverManager.chromedriver().watch());
                    driverInstance = wdm.get().create();
                    break;
                case "edge":
                    setWDM(WebDriverManager.edgedriver().watch());
                    driverInstance = wdm.get().create();
                    break;
                case "firefox":
                    setWDM(WebDriverManager.firefoxdriver());
                    driverInstance = wdm.get().create();
                    break;
                default:
                    throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
            }

            driver.set(driverInstance);
            driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

             driverInstance.manage().window().maximize();
             driverInstance.get(Constants.URL);

            LogHelper.getLogger().info("Navigated to the URL with browser: {}", browser);
        } catch (Exception e) {
            LogHelper.getLogger().error("Setup failed: {}", e.getMessage());
        }
    }

    @AfterTest(alwaysRun = true)
    public void wrapUp() {
        if ( driver != null ) {
            driver.get().quit();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriverManager getWDM() {
        return wdm.get();
    }

    public static void setWDM(WebDriverManager _wdm) {
        wdm.set(_wdm);
    }



}
