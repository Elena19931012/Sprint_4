package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    // Локаторы
    // Кнопка "Заказать" (в верхней части страницы)
    private By orderButtonTop = By.xpath("//button[text()='Заказать']");

    // Кнопка "Заказать" (в нижней части страницы)
    private By orderButtonBottom = By.xpath("//button[contains(@class, 'Button_Button__ra12g')]");

    // Кнопка "Статус заказа"
    private By orderStatusButton = By.xpath("//button[text()='Статус заказа']");

    // Поле ввода номера заказа
    private By orderNumberInput = By.xpath("//input[@placeholder='Введите номер заказа']");

    // Кнопка "Go!"
    private By goButton = By.xpath("//button[text()='Go!']");

    // Раздел "Вопросы о важном"
    private By faqSection = By.xpath("//div[contains(@class, 'Home_FAQ__3uVm4')]");

    // Кнопка "да все привыкли" (куки)
    private By cookieButton = By.id("rcc-confirm-button");

    // FAQ локаторы
    private By faqQuestions = By.xpath("//div[@data-accordion-component='AccordionItemButton']");
    private By faqAnswers = By.xpath("//div[@data-accordion-component='AccordionItemPanel']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    public void enterOrderNumber(String orderNumber) {
        driver.findElement(orderNumberInput).sendKeys(orderNumber);
    }

    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    public void clickFaqQuestion(int index) {
        driver.findElements(faqQuestions).get(index).click();
    }

    public String getFaqAnswer(int index) {
        return driver.findElements(faqAnswers).get(index).getText();
    }

    public boolean isFaqAnswerVisible(int index) {
        return driver.findElements(faqAnswers).get(index).isDisplayed();
    }
}