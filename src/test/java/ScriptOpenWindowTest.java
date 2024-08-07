import configuration.WebDriverConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.AssertText;

import java.util.Set;

public class ScriptOpenWindowTest {

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
    void testOpenWindow() {
        driver.get("http://the-internet.herokuapp.com/windows");

        WebElement link = driver.findElement(By.linkText("Click Here"));
        link.click();

        String mainWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        String newWindow = windowHandles.stream()
                .filter(handle -> !handle.equals(mainWindow))
                .findFirst()
                .orElse(null);

        AssertText.assertWindowNotNull(newWindow, "Значение не должно быть равным нулю");

        driver.switchTo().window(newWindow);
        WebElement newPageText = driver.findElement(By.tagName("h3"));
        AssertText.assertTextEquals(newPageText.getText(), "New Window");
        driver.close();

        driver.switchTo().window(mainWindow);
        WebElement mainPageText = driver.findElement(By.tagName("h3"));
        AssertText.assertTextEquals(mainPageText.getText(), "Opening a new window");
    }
}