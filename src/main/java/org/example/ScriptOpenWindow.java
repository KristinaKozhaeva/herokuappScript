package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.AssertText;

import java.util.Set;

public class ScriptOpenWindow {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));

        driver.get("http://the-internet.herokuapp.com/windows");

        WebElement link = driver.findElement(By.linkText("Click Here"));
        link.click();

        String mainWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        String newWindowHandle = null;

        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                newWindowHandle = handle;
                break;
            }
        }

        if (newWindowHandle != null) {
            driver.switchTo().window(newWindowHandle);

            WebElement newPageText = driver.findElement(By.tagName("h3"));
            AssertText.assertTextEquals(newPageText.getText(), "New Window");

            driver.close();
            driver.switchTo().window(mainWindowHandle);

            WebElement mainPageText = driver.findElement(By.tagName("h3"));
            AssertText.assertTextEquals(mainPageText.getText(), "Opening a new window");
        }
        driver.quit();
    }
}