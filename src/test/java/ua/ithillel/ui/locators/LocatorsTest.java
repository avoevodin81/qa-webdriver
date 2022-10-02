package ua.ithillel.ui.locators;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import ua.ithillel.ui.BaseTest;

public class LocatorsTest extends BaseTest {

    @Test
    public void test() {
        WebDriver driver = getWebDriver();
        driver.navigate().to("https://www.w3schools.com/html/html5_draganddrop.asp");


        WebElement dragElement = driver.findElement(By.id("div1"));
        WebElement dropElement = driver.findElement(By.id("div2"));

        WebElement movedElement = driver.findElement(By.id("drag1"));

        new Actions(driver)
                .moveToElement(movedElement)
                .clickAndHold()
                .moveToElement(dropElement)
                .release()
                .perform();


        System.out.println();

    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
