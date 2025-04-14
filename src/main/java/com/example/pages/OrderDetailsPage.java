package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderDetailsPage {
    private WebDriver driver;

    // Локаторы
    private By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodDropdown = By.className("Dropdown-control");
    private By rentalPeriodOption = By.xpath("//div[@class='Dropdown-menu']//div[text()='сутки']");
    private By blackColorCheckbox = By.id("black");
    private By greyColorCheckbox = By.id("grey");
    private By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By backButton = By.xpath("//button[text()='Назад']");
    private By orderButton = By.xpath("//button[text()='Заказать']");

    // Локаторы для модальных окон
    private By modalYesButton = By.xpath("//button[text()='Да']");
    private By modalNoButton = By.xpath("//button[text()='Нет']");
    private By orderConfirmationText = By.xpath("//div[contains(@class, 'Order_Text__2broi')]");
    private By viewStatusButton = By.xpath("//button[text()='Посмотреть статус']");

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(dateInput).sendKeys(org.openqa.selenium.Keys.TAB); 
    }

    public void selectRentalPeriod(String period) {
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(By.xpath("//div[@class='Dropdown-menu']//div[text()='" + period + "']")).click();
    }

    public void selectBlackColor() {
        driver.findElement(blackColorCheckbox).click();
    }

    public void selectGreyColor() {
        driver.findElement(greyColorCheckbox).click();
    }

    public void enterComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void clickBackButton() {
        driver.findElement(backButton).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        driver.findElement(modalYesButton).click();
    }

    public String getOrderConfirmationText() {
        return driver.findElement(orderConfirmationText).getText();
    }

    public void clickViewStatusButton() {
        driver.findElement(viewStatusButton).click();
    }
}