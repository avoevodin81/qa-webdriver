package ua.ithillel.ui.capabilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CapabilitiesTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        options.setCapability("acceptInsecureCerts", true);
        options.addArguments("--ignore-certificate-errors-spki-list");
        options.addArguments("--start-fullscreen");

        options.asMap().forEach((k, v) -> System.out.println(k + " - " + v));

    }

    @Test
    public void test() {
        driver.navigate().to("https://expired.badssl.com/");

        System.out.println();
    }

    @AfterTest
    public void stop() {
        driver.quit();
    }
}
