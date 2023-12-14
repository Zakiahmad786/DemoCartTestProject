package com.ga.democart.tests;

import com.qa.democart.factory.DriverFactory;
import com.qa.democart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    DriverFactory df;
    Properties prop;
    WebDriver driver;
    LoginPage loginPage;
    AccountsPage accPage;
    ResultsPage resultsPage;
    ProductInfoPage productInfoPage;
    RegistrationPage regPage;
    @BeforeTest
    public void setup(){
        df=new DriverFactory();
        prop=df.initProp();
        driver=df.initDriver(prop);
        loginPage=new LoginPage(driver);
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
