package com.moda;

import com.moda.basetc.BaseTest;
import com.moda.core.Constants;
import com.moda.pages.LoginPage;
import org.testng.annotations.Test;

public class ValidLoginTC extends BaseTest {

    String userName = Constants.USER;
    String password = Constants.PASSWORD;

    @Test(priority = 2, description = "Valid Credential login")
    public void ValidLoginTest(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(userName);
        loginPage.inputPassword(password);
        loginPage.submitLogin();
    }
}
