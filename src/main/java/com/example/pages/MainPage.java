package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Локаторы
    private By cookiesButton = By.id("rcc-confirm-button");
    private By orderButtonTop = By.className("Button_Button__ra12g");
    private By orderButtonBottom = By.xpath("//div[contains(@class, 'Home_FinishButton')]/button");
    private By orderStatusButton = By.className("Header_Link__1TAG7");
    private By orderNumberInput = By.xpath("//input[@placeholder='Введите номер заказа']");
    private By goButton = By.xpath("//button[text()='Go!']");
    private By faqSection = By.xpath("//div[contains(@class, 'Home_FAQ__3uVm4')]");
    private By faqQuestions = By.xpath("//div[@data-accordion-component='AccordionItem']");
    private By faqAnswers = By.xpath("//div[@data-accordion-component='AccordionItemPanel']");
    
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Метод для прокрутки страницы вниз
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    // Метод для ожидания элемента
    public WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public void acceptCookies() {
        waitForElement(cookiesButton).click();
    }
    
    // Метод для работы с FAQ
    public void openFaqSection() {
        scrollToBottom();
        waitForElement(faqSection);
    }
    
    public void clickFaqQuestion(int index) {
        List<WebElement> questions = driver.findElements(faqQuestions);
        questions.get(index).click();
    }
    
    public boolean isFaqAnswerVisible(int index) {
        List<WebElement> answers = driver.findElements(faqAnswers);
        wait.until(ExpectedConditions.visibilityOf(answers.get(index)));
        return answers.get(index).isDisplayed();
    }
    
    public String getFaqAnswer(int index) {
        List<WebElement> answers = driver.findElements(faqAnswers);
        return answers.get(index).getText();
    }
    
    public void clickOrderButtonTop() {
        waitForElement(orderButtonTop).click();
    }
    
    public void clickOrderButtonBottom() {
        scrollToBottom();
        waitForElement(orderButtonBottom).click();
    }
    
    public void clickOrderStatusButton() {
        waitForElement(orderStatusButton).click();
    }
    
    public void enterOrderNumber(String orderNumber) {
        waitForElement(orderNumberInput).sendKeys(orderNumber);
    }
    
    public void clickGoButton() {
        wait.until(ExpectedConditions.elementToBeClickable(goButton)).click();
    }
    
    // Метод для проверки URL
    public boolean currentUrlContains(String text) {
        return driver.getCurrentUrl().contains(text);
    }
}