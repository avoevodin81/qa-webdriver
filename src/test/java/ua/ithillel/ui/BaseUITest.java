package ua.ithillel.ui;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ua.ithillel.ui.utils.ConfigProvider;

public class BaseUITest extends BaseTest {

    @Test
    public void test() {
        getWebDriver().navigate().to(ConfigProvider.BASE_URL);
        getWebDriver().findElement(By.name("q")).sendKeys("webdriver");
        getWebDriver().findElements(By.name("btnK")).get(0).click();

        Assert.assertTrue(getWebDriver().getTitle().contains("webdriver"));
    }

}
