import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.scooter.*;

@RunWith(Parameterized.class)
public class OrderTest {

    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String calendar;
    private String duration;
    private String color;
    private String comment;
    private boolean result;
    private WebDriver driver;

    public OrderTest(String name, String surname, String address, String metro, String phone, String calendar, String duration, String color, String comment, boolean result) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.calendar = calendar;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] getDataForForm() {
        return new Object[][] {
                {"Дмитрий", "Дмитриев", "Студенческая 5", "Черкизовская", "88007008000", "25.12.2022", "сутки", "чёрный жемчуг", "Удачного дня", true},
                {"Григорий", "Григорьев", "Бездельнеческая 8", "Черкизовская", "88007008001", "26.12.2022", "двое суток", "серая безысходность", "Всего плохого", true},
        };
    }

    @Test
    public void Firefox() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderPages orderPage = new OrderPages(driver);

        // кликаем на кнопку "Заказать" в хедере
        orderPage.clickOnButtonOrderAtHeader();

        // вводим данные на первой странице заказа
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.setMetro(metro);
        orderPage.setPhone(phone);

        // проверяем, что кнопка "Назад" на второй странице рабочая, и возвращаемся на вторую страницу
        orderPage.clickOnFirstNextButton();
        orderPage.clickOnBackButton();
        orderPage.clickOnFirstNextButton();

        // вводим данные на второй странице заказа
        orderPage.setCalendar(calendar);
        orderPage.setDuration(duration);
        orderPage.setColor(color);
        orderPage.setComment(comment);

        // проверяем, что кнопка "Нет" в попапе рабочая, и возвращаемся на попап
        orderPage.clickOnSecondNextButton();
        orderPage.clickOnSecondBackButton();
        orderPage.clickOnSecondNextButton();

        // кликаем на кнопку "Да" и проверяем, что итоговый попап появился
        orderPage.clickOnThirdNextButton();
        orderPage.assertPopupOrder(result);
    }

    @Test
    public void Chrome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderPages orderPage = new OrderPages(driver);

        // кликаем на кнопку "Заказать" в мейне
        orderPage.clickOnButtonOrderAtMiddle();

        // вводим данные на первой странице заказа
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.setMetro(metro);
        orderPage.setPhone(phone);

        // проверяем, что кнопка "Назад" на второй странице рабочая, и возвращаемся на вторую страницу
        orderPage.clickOnFirstNextButton();
        orderPage.clickOnBackButton();
        orderPage.clickOnFirstNextButton();

        // вводим данные на второй странице заказа
        orderPage.setCalendar(calendar);
        orderPage.setDuration(duration);
        orderPage.setColor(color);
        orderPage.setComment(comment);

        // проверяем, что кнопка "Нет" в попапе рабочая, и возвращаемся на попап
        orderPage.clickOnSecondNextButton();
        orderPage.clickOnSecondBackButton();
        orderPage.clickOnSecondNextButton();

        // кликаем на кнопку "Да" и проверяем, что итоговый попап появился
        orderPage.clickOnThirdNextButton();
        orderPage.assertPopupOrder(result);
    }


    @After
    public void teardown() {
        driver.quit();
    }
}