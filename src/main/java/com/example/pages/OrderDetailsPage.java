package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodDropdown = By.className("Dropdown-control");
    private By rentalPeriodOption = By.xpath("//div[@class='Dropdown-menu']//div[text()='сутки']");
    private By blackColorCheckbox = By.id("black");
    private By greyColorCheckbox = By.id("grey");
    private By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By backButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM.Button_Inverted__3IF-i");
    private By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]/button[text()='Заказать']");
    private By rentalHeader = By.xpath("//div[text()='Про аренду']");

    // Локаторы для модальных окон
    private By confirmOrderModal = By.className("Order_Modal__YZ-d3");
    private By modalYesButton = By.xpath("//button[text()='Да']");
    private By modalNoButton = By.xpath("//button[text()='Нет']");
    private By orderConfirmationText = By.xpath("//div[contains(@class, 'Order_Text__2broi')]");
    private By viewStatusButton = By.xpath("//button[text()='Посмотреть статус']");

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        // Добавляем ожидание кликабельности кнопки и скроллинг к ней
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton));
            
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", 
                driver.findElement(orderButton));
            
            Thread.sleep(500);
            
            wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        } catch (Exception e) {
            System.out.println("Не удалось нажать на кнопку заказа: " + e.getMessage());
            try {
                // Альтернативный способ клика через JavaScript
                org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(orderButton));
            } catch (Exception jsEx) {
                try {
                    driver.findElement(By.xpath("//button[contains(text(), 'Заказать') and not(contains(@class, 'Inverted'))]")).click();
                } catch (Exception finalEx) {
                    throw finalEx;
                }
            }
        }
    }

    public void confirmOrder() {
        // Дождаться появления модального окна
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmOrderModal));
        // Теперь можно нажать кнопку "Да"
        wait.until(ExpectedConditions.elementToBeClickable(modalYesButton)).click();
    }

    public String getOrderConfirmationText() {
        return driver.findElement(orderConfirmationText).getText();
    }

    public void clickViewStatusButton() {
        driver.findElement(viewStatusButton).click();
    }

    // Комбинированный метод для заполнения всех деталей заказа
    public void fillOrderDetails(String date, String rentalPeriod, String comment) {
        enterDate(date);
        // Клик по заголовку для закрытия календаря
        driver.findElement(rentalHeader).click();
        selectRentalPeriod(rentalPeriod);
        selectBlackColor();
        enterComment(comment);
    }

    // Метод для заполнения и оформления заказа
    public void fillOrderDetailsAndPlaceOrder(String date, String rentalPeriod, String comment) {
        fillOrderDetails(date, rentalPeriod, comment);
        clickOrderButton();
        
        // Добавляем небольшую задержку перед подтверждением заказа
        try {
            Thread.sleep(1000); // Иногда нужна небольшая пауза для загрузки модального окна
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        confirmOrder();
        
        // Ожидание подтверждения заказа
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationText));
    }
}