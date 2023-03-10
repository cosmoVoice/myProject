package lesson5Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
import io.qameta.allure.*;
public class SearchInputTest {
    static WebDriver driver;
    @Test
    @DisplayName("Поиск через поисковую строку")
    @Description("Поиск товара с использованием поисковой строки")
    @Severity(SeverityLevel.NORMAL)
    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @BeforeEach
    void getPage() {
        driver.get("https://www.dogeat.ru/");
    }
    @Test
    void test() throws InterruptedException {
        if (!driver.findElements(By.id("popmechanic-snippet")).isEmpty()) {
            WebElement ModalCircle = driver.findElement(By.id("popmechanic-snippet"));
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].remove()", ModalCircle);
            }
        }
        WebElement webElement5 = driver.findElement(By.xpath("//*[@id=\"mainBlock\"]/div[2]/div/div/div/form/div[1]"));
        webElement5.click();
        Thread.sleep(5000);

        WebElement webElement6 = driver.findElement(By.cssSelector("#search-input"));
        webElement6.sendKeys("ошейник");

        WebElement webElement7 = driver.findElement(By.xpath("//*[@id=\"mainBlock\"]/div[2]/div/div/div/form/div[2]/input"));
        webElement7.click();

        //результаты поиска
        WebElement webElement8 = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[3]/div/div/div[2]/h1[2]"));
        Assertions.assertEquals("Результаты поиска: ошейник", webElement8.getText());
    }
    // @AfterAll
    //static void close(){
    //   driver.quit();
    //}
}
