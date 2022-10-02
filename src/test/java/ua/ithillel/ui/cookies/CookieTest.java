package ua.ithillel.ui.cookies;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

public class CookieTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.navigate().to("https://google.com/");

        Set<Cookie> cookies = driver.manage().getCookies();

        Cookie cookie = driver.manage().getCookieNamed("NID");

        driver.manage().addCookie(new Cookie("name", "value"));

        driver.manage().deleteCookie(new Cookie("name", "value"));

        driver.manage().deleteAllCookies();

        System.out.println();
    }

    @AfterTest
    public void stop() {
        driver.quit();
    }
}
