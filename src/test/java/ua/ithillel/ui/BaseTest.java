package ua.ithillel.ui;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ua.ithillel.ui.browser.WebDriverFactory;

import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void beforeMethod() {
        WebDriver driver = new WebDriverFactory().getDriver();
        setWebDriver(driver);

        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }


    @AfterMethod
    public void afterMethod() {
        getWebDriver().quit();
    }

    private void setWebDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public WebDriver getWebDriver() {
        return driverThreadLocal.get();
    }
}
