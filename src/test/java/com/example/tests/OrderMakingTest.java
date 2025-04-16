package com.example.tests;

import com.example.pages.MainPage;
import com.example.pages.OrderPage;
import com.example.pages.OrderDetailsPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderMakingTest extends BaseTest {
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
    @Parameterized.Parameters(name = "Заказ для: {0} {1}, метро: {3}, период: {6}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
            {"Иван", "Иванов", "Москва, ул. Ленина, д. 1", "Лубянка", "89991234567", "12.04.2025", "сутки"},
            {"Анна", "Петрова", "Санкт-Петербург, Невский проспект, д. 10", "Динамо", "89991112233", "15.04.2025", "двое суток"}
        });
    }

    @Before
    @Override
    public void setUp() {
        super.setUp();
        driver.get(MainPage.BASE_URL + "order");
        orderPage = new OrderPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);
    }

    @Test
    public void testFullOrderFlow() {
        // Шаг 1: Заполнение формы на первой странице и переход ко второму шагу
        orderDetailsPage = orderPage.fillOrderFormAndContinue(name, surname, address, metroStation, phone);

        // Шаг 2: Заполнение деталей аренды и оформление заказа
        orderDetailsPage.fillOrderDetailsAndPlaceOrder(date, rentalPeriod, "Оставьте у двери");

        // Проверка текста подтверждения заказа
        String confirmationText = orderDetailsPage.getOrderConfirmationText();
        assertTrue("Проверка текста подтверждения заказа", confirmationText.contains("Номер заказа"));
    }
}