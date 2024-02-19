package com.moda.pages;


import com.moda.utils.ExplicitWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DashboardPage.class);


    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String welcomeMessageXpath = "//div[contains(text(),'Stacey, welcome to your Member Dashboard')]";
    @FindBy(xpath = welcomeMessageXpath )
    private WebElement welcomeMessage;

    public String welcomeMessage(){
        String welcomeMessageText = "";
        try {
            new ExplicitWait(driver).waitForElement(60, By.xpath(welcomeMessageXpath));
            welcomeMessageText = welcomeMessage.getText();
            logger.info("Dashboard Welcome text: {}", welcomeMessageText); // Log the username input action
        } catch (TimeoutException timeout){
            timeout.printStackTrace();
        }
        return welcomeMessageText;
    }
}
