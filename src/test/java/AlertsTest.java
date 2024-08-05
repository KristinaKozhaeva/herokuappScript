import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.AssertText;

public class AlertsTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));

        driver.get("http://the-internet.herokuapp.com/javascript_alerts");

        WebElement jsAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        jsAlertButton.click();

        Alert jsAlert = driver.switchTo().alert();
        AssertText.assertTextEquals(jsAlert.getText(), "I am a JS Alert");
        jsAlert.accept();

        WebElement jsConfirmButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        jsConfirmButton.click();
        Alert jsConfirm = driver.switchTo().alert();
        jsConfirm.dismiss();

        WebElement jsPromptButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        jsPromptButton.click();
        Alert jsPrompt = driver.switchTo().alert();
        jsPrompt.sendKeys("Hello World");
        jsPrompt.accept();

        WebElement resultText = driver.findElement(By.id("result"));
        AssertText.assertTextEquals(resultText.getText(), "You entered: Hello World");

        driver.quit();
    }
}
