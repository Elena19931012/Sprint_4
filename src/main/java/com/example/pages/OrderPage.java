package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    // Локаторы
    private By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationInput = By.xpath("//input[@placeholder='* Станция метро']");
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
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
        driver.findElement(By.xpath("//div[contains(text(), '" + station + "')]")).click();
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}