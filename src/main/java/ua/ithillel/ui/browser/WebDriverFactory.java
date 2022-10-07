package ua.ithillel.ui.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import ua.ithillel.ui.utils.ConfigProvider;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class WebDriverFactory {

    private static final String BROWSER = System.getProperty("browser", ConfigProvider.BROWSER);
    private WebDriver driver;

    public WebDriver getDriver() {
        driver = getDriver(Browser.valueOf(BROWSER.toUpperCase()));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigProvider.IMPLICITLY_WAIT));
        Runtime.getRuntime().addShutdownHook(new Thread(new CloseDriverHook(driver)));
        return driver;
    }

    private WebDriver getDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFirefoxDriver();
            case SELENOID_CHROME:
                return getSelenoidChromeDriver();
            default:
                throw new IllegalArgumentException("Wrong browser type provided. Choices are: chrome, firefox");
        }
    }

    private WebDriver getSelenoidChromeDriver() {
        if (driver == null) {
            DesiredCapabilities browser = new DesiredCapabilities();
            browser.setBrowserName("chrome");
//            browser.setVersion("100.0");
            browser.setCapability("enableVNC", true);

            try {
                RemoteWebDriver remoteWebDriver = new RemoteWebDriver(
                        URI.create(ConfigProvider.SELENOID_HUB).toURL(),
                        browser);
                remoteWebDriver.manage().window().setSize(new Dimension(1280, 1024));
                remoteWebDriver.setFileDetector(new LocalFileDetector());
                driver = remoteWebDriver;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
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
