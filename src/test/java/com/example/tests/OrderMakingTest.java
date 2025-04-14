package com.example.tests;

import com.example.pages.OrderPage;
import com.example.pages.OrderDetailsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderMakingTest {
    private WebDriver driver;
    private OrderPage orderPage;
    private OrderDetailsPage orderDetailsPage;

    // Параметры для теста
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentalPeriod;

    // Конструктор для параметров
    public OrderMakingTest(String name, String surname, String address, String metroStation, String phone, String date, String rentalPeriod) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
    }

    // Наборы данных для теста
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
            {"Иван", "Иванов", "Москва, ул. Ленина, д. 1", "Лубянка", "89991234567", "12.04.2025", "сутки"},
            {"Анна", "Петрова", "Санкт-Петербург, Невский проспект, д. 10", "Динамо", "89991112233", "15.04.2025", "двое суток"}
        });
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:\\Users\\Kalken.M\\Desktop\\Temp\\3\\yandex-scooter\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        orderPage = new OrderPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);
    }

    @Test
    public void testFullOrderFlow() {
        // Шаг 1: Заполнение формы на первой странице
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.selectMetroStation(metroStation);
        orderPage.enterPhone(phone);

        // Нажатие кнопки "Далее"
        orderPage.clickNextButton();

        By headerLocator = By.xpath("//div[text()='Про аренду']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        assertTrue("Проверка перехода на следующий шаг", wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocator)).isDisplayed());

        // Шаг 2: Проверка элементов на второй странице
        orderDetailsPage.enterDate(date);
        driver.findElement(By.xpath("//div[text()='Про аренду']")).click();
        orderDetailsPage.selectRentalPeriod(rentalPeriod);
        orderDetailsPage.selectBlackColor();
        orderDetailsPage.enterComment("Оставьте у двери");

        // Нажатие кнопки "Заказать"
        orderDetailsPage.clickOrderButton();

        // Подтверждение заказа в модальном окне
        orderDetailsPage.confirmOrder();

        // Проверка текста подтверждения заказа
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'Order_Text__2broi')]")));
        String confirmationText = orderDetailsPage.getOrderConfirmationText();
        assertTrue("Проверка текста подтверждения заказа", confirmationText.contains("Номер заказа"));

        // Нажатие кнопки "Посмотреть статус"
        orderDetailsPage.clickViewStatusButton();

        // Проверка перехода на страницу статуса заказа
        assertTrue("Проверка перехода на страницу статуса заказа", driver.getCurrentUrl().contains("track"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}