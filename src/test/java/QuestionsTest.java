import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Assert;
import ru.scooter.*;

@RunWith(Parameterized.class)
public class QuestionsTest {

    private String answer;
    private int orderNumber;
    private WebDriver driver;

    public QuestionsTest(String answer, int orderNumber) {
        this.answer = answer;
        this.orderNumber = orderNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getAnswer() {
        return new Object[][] {
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 1},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 2},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 3},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 4},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 5},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 6},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 7},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 8},
        };
    }

    @Test
    public void clickOnQuestionAtFirefox() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);

        // скролл до элемента с вопросом, передается порядковый номер
        mainPage.scroolToQuestions(orderNumber);

        // клик по вопросу, передается порядковый номер
        mainPage.clickOnQuestion(orderNumber);

        // сравниваем текст ответа со страницы с текстом из тестового набора
        Assert.assertEquals(answer, mainPage.getTextOfAccordionItem(orderNumber));
    }

    @Test
    public void clickOnQuestionAtChrome() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.scroolToQuestions(orderNumber);
        mainPage.clickOnQuestion(orderNumber);
        Assert.assertEquals(answer, mainPage.getTextOfAccordionItem(orderNumber));
    }


    @After
    public void teardown() {
        driver.quit();
    }
}