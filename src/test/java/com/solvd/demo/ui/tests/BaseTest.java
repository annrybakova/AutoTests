package com.solvd.demo.ui.tests;

import com.zebrunner.carina.core.IAbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.zebrunner.carina.utils.R;


public class BaseTest implements IAbstractTest {

    // private WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        getDriver().manage().window().maximize();
    }

    @BeforeSuite
    public void setup() {
        R.CONFIG.put("selenium_url", "http://localhost:4444", true);
        R.CONFIG.put("url", "https://anna-rybakova.42web.io", true);
        R.CONFIG.put("browser", "chrome", true);
        R.CONFIG.put("capabilities.browserName", "chrome", true);
        R.CONFIG.put("capabilities.platformName", "Windows", true);
    }

    //     public WebDriver getDriver() {
    //     return driver;
    // }
}

