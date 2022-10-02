package ua.ithillel.ui.multithreading;

import ua.ithillel.ui.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class MultithreadingTest extends BaseTest {

    @Test
    public void test1() throws InterruptedException {
        WebDriver driver = getWebDriver();

        driver.navigate().to("https://google.com");
        Thread.sleep(2000);
    }

    @Test
    public void test2() throws InterruptedException {
        getWebDriver().navigate().to("https://google.com");
        Thread.sleep(2000);
    }
}
