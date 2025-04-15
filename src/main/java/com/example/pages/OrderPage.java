package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationInput = By.xpath("//input[@placeholder='* Станция метро']");
    private By metroStationDropdownItem = By.xpath("//div[@class='select-search__select']//*[contains(@class, 'select-search__row')]");
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");
    private By rentalHeader = By.xpath("//div[text()='Про аренду']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void enterSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void enterAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void selectMetroStation(String station) {
        driver.findElement(metroStationInput).sendKeys(station);
        driver.findElement(metroStationDropdownItem).click();
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Комбинированный метод для заполнения всей формы заказа
    public void fillOrderForm(String name, String surname, String address, String metroStation, String phone) {
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        selectMetroStation(metroStation);
        enterPhone(phone);
    }

    // Метод для заполнения и перехода на следующую страницу
    public OrderDetailsPage fillOrderFormAndContinue(String name, String surname, String address, String metroStation, String phone) {
        fillOrderForm(name, surname, address, metroStation, phone);
        clickNextButton();
        
        // Ожидание загрузки следующей страницы
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentalHeader));
        
        return new OrderDetailsPage(driver);
    }
}