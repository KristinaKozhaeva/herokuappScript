import configuration.WebDriverConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.AssertText;

public class AlertsTest {

    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverConfig.createDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();

    }

    @Test
    @DisplayName("Получение текста в алерте - I am a JS Alert")
    void testJSAlert() {
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");

        WebElement jsAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        jsAlertButton.click();

        Alert jsAlert = driver.switchTo().alert();
        AssertText.assertTextEquals(jsAlert.getText(), "I am a JS Alert");
        jsAlert.accept();
    }

    @Test
    @DisplayName("Отказ от алерта")
    void testJSConfirm() {
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");

        WebElement jsConfirmButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        jsConfirmButton.click();

        Alert jsConfirm = driver.switchTo().alert();
        jsConfirm.dismiss();
    }

    @Test
    @DisplayName("Вывод текста Hello World")
    void testJSPrompt() {
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");

        WebElement jsPromptButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        jsPromptButton.click();

        Alert jsPrompt = driver.switchTo().alert();
        jsPrompt.sendKeys("Hello World");
        jsPrompt.accept();

        WebElement resultText = driver.findElement(By.id("result"));
        AssertText.assertTextEquals(resultText.getText(), "You entered: Hello World");
    }
}