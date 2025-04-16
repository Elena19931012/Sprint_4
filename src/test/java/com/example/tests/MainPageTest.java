package com.example.tests;

import com.example.pages.MainPage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainPageTest extends BaseTest {
    private MainPage mainPage;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        driver.get(MainPage.BASE_URL);
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testOrderButtonTop() {
        mainPage.clickOrderButtonTop();
        assertTrue("Проверка перехода по кнопке 'Заказать' (вверху)", mainPage.currentUrlContains("order"));
    }

    @Test
    public void testOrderButtonBottom() {
        mainPage.clickOrderButtonBottom();
        assertTrue("Проверка перехода по кнопке 'Заказать' (внизу)", mainPage.currentUrlContains("order"));
    }

    @Test
    public void testOrderStatus() {
        mainPage.clickOrderStatusButton();
        mainPage.enterOrderNumber("12345");
        mainPage.clickGoButton();
        assertTrue("Проверка перехода по кнопке 'Go!'", mainPage.currentUrlContains("track"));
    }
}