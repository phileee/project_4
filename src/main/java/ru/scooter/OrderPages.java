package ru.scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.junit.Assert;

public class OrderPages {

    // кнопка заказа в хедере на главной странице
    private final By buttonOrderAtHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");

    // кнопка заказа в центре главной страницы
    private final By buttonOrderAtMiddle = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // поле "Имя"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");

    // поле "Фамилия"
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    // поле "Адрес"
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // поле-список "Метро"
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");

    // поле "Телефон"
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // кнопка "Далее" на первой странице
    private final By firstNextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // поле "Календарь"
    private final By calendarField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // поле "Срок аренды"
    private final By durationField = By.cssSelector(".Dropdown-placeholder");

    // чекбокс с вариантом "черный"
    private final By checkboxColorBlack = By.xpath(".//input[@id='black']");

    // чекбокс с вариантом "серый"
    private final By checkboxColorGrey = By.xpath(".//input[@id='grey']");

    // поле "Комментарий для курьера"
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // кнопка "Назад"
    private final By backButton = By.xpath(".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i') and (text()='Назад')]");

    // кнопка "Заказать" на второй странице
    private final By secondNextButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[(@class='Button_Button__ra12g Button_Middle__1CSJM') and (text()='Заказать')]");

    // кнопка "Нет" в попапе
    private final By secondBackButton = By.xpath(".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i') and (text()='Нет')]");

    // кнопка "Да" в попапе
    private final By thirdNextButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[(@class='Button_Button__ra12g Button_Middle__1CSJM') and (text()='Да')]");

    // попап с номером заказа
    private final By popupOrder = By.xpath(".//div[(@class='Order_Text__2broi') and (contains(text(), 'Номер заказа:'))]");
    private WebDriver driver;

    public OrderPages(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnButtonOrderAtHeader() {
        driver.findElement(buttonOrderAtHeader).click();
    }

    public void clickOnButtonOrderAtMiddle() {
        WebElement element = driver.findElement(By.xpath(".//*[text()='Курьер забирает самокат']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(buttonOrderAtMiddle).click();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setMetro(String metro) {
        driver.findElement(metroField).click();
        driver.findElement(By.xpath(".//button[@class='Order_SelectOption__82bhS select-search__option']/div[(@class='Order_Text__2broi') and (text() = '"+ metro +"')]")).click();
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickOnFirstNextButton() {
        driver.findElement(firstNextButton).click();
    }

    public void setCalendar(String calendar) {
        driver.findElement(calendarField).sendKeys(calendar);
        driver.findElement(By.cssSelector(".Order_Header__BZXOb")).click();
    }

    public void setDuration(String duration) {
        driver.findElement(durationField).click();
        driver.findElement(By.xpath(".//div[(@class='Dropdown-option') and (text()='"+ duration +"')]")).click();
    }

    public void setColor(String color) {
        if (color == "чёрный жемчуг") {
            driver.findElement(checkboxColorBlack).click();
        } else if (color == "серая безысходность") {
            driver.findElement(checkboxColorGrey).click();
        }
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOnBackButton() {
        driver.findElement(backButton).click();
    }

    public void clickOnSecondNextButton() {
        driver.findElement(secondNextButton).click();
    }

    public void clickOnSecondBackButton() {
        driver.findElement(secondBackButton).click();
    }

    public void clickOnThirdNextButton() {
        driver.findElement(thirdNextButton).click();
    }

    public void assertPopupOrder(boolean result) {
        Assert.assertEquals(result, driver.findElement(popupOrder).isDisplayed());
    }
}
