package com.example.tests;

import com.example.util.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
