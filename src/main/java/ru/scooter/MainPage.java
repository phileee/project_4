package ru.scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    // раздел "Вопросы о важном"
    private By questions = By.cssSelector(".Home_FourPart__1uthg");
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    // метод для скролла до раздела "Вопросы о важном"
    public void scroolToQuestions(int index) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("(.//div[@class='accordion__heading'])["+ index +"]")));
        WebElement element = driver.findElement(questions);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // метод клика по вопросу с номером индекса
    public void clickOnQuestion(int index) {
        driver.findElement(By.xpath("(.//div[@class='accordion__heading'])["+ index +"]")).click();
    }

    // метод, принимающий номер индекса вопроса и возвращающий текст ответа
    public String getTextOfAccordionItem(int index) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//div[@class='accordion__panel'])["+ index +"]/p")));
        return driver.findElement(By.xpath("(.//div[@class='accordion__panel'])["+ index +"]/p")).getText();
    }
}
