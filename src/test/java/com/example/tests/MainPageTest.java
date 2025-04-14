package com.example.tests;

import com.example.pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:\\Users\\Kalken.M\\Desktop\\Temp\\3\\yandex-scooter\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testFaqSection() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'Home_FAQ__3uVm4')]")));

        // Проверка всех вопросов в разделе FAQ
        for (int i = 0; i < 8; i++) { 
            mainPage.clickFaqQuestion(i);
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//div[@data-accordion-component='AccordionItemPanel']")).get(i)));
            assertTrue("Ответ на вопрос FAQ должен быть видимым", mainPage.isFaqAnswerVisible(i));
            String answer = mainPage.getFaqAnswer(i);
            assertTrue("Ответ на вопрос FAQ не должен быть пустым", !answer.isEmpty());
        }
    }

    @Test
    public void testOrderButtonTop() {
        mainPage.clickOrderButtonTop();
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Проверка перехода по кнопке 'Заказать' (вверху)", currentUrl.contains("order"));
    }

    @Test
    public void testOrderButtonBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'Button_Button__ra12g')]")));
        mainPage.clickOrderButtonBottom();
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Проверка перехода по кнопке 'Заказать' (внизу)", currentUrl.contains("order"));
    }

    @Test
    public void testOrderStatus() {
        mainPage.clickOrderStatusButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Введите номер заказа']")));
        mainPage.enterOrderNumber("12345");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Go!']")));
        mainPage.clickGoButton();
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Проверка перехода по кнопке 'Go!'", currentUrl.contains("track"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}