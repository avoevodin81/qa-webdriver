package ua.ithillel.ui.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.ithillel.ui.utils.ConfigProvider;

public class WebDriverFactory {

    private static final String BROWSER = System.getProperty("browser", ConfigProvider.BROWSER);
    private WebDriver driver;

    public WebDriver getDriver() {
        driver = getDriver(Browser.valueOf(BROWSER.toUpperCase()));
        return driver;
    }

    private WebDriver getDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFirefoxDriver();
            default:
                throw new IllegalArgumentException("Wrong browser type provided. Choices are: chrome, firefox");
        }
    }

    private WebDriver getFirefoxDriver() {
        if (driver == null) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        return driver;
    }

    private WebDriver getChromeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }
}
