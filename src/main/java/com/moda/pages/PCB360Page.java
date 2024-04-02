package com.moda.pages;

import com.moda.pages.base.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class PCB360Page extends BasePage {
    WebDriver driver;

    public PCB360Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private static final String PCB360_LATERAL_TAB_XPATH = "(//div[contains(@class, 'sl-lateral-active-tab')])[2]";

    @Step("Check if PCB360 Lateral Tab is displayed")
    public boolean isPCB360LateralTabDisplayed() {
        return isElementDisplayed( driver, 60,  "is PCB360 Lateral Tab displayed", PCB360_LATERAL_TAB_XPATH);
    }
}
