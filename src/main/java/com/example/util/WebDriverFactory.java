package com.example.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    
    private static final String DEFAULT_BROWSER = "chrome";
    
    public static WebDriver createWebDriver() {
        String browserName = System.getProperty("browser", DEFAULT_BROWSER).toLowerCase();
        
        WebDriver driver;
        switch (browserName) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "c:\\Users\\Kalken.M\\Desktop\\Temp\\3\\yandex-scooter\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                System.setProperty("webdriver.chrome.driver", "c:\\Users\\Kalken.M\\Desktop\\Temp\\3\\yandex-scooter\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }
        
        driver.manage().window().maximize();
        
        return driver;
    }
}
