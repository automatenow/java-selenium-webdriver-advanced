package io.automatenow.tests;

import io.automatenow.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

/**
 * @author Marco A. Cruz
 */
public class TestRelativeLocators extends BaseTest {

    @Test(description = "Makes use of relative locators in Selenium 4")
    public void testRelativeLocators() {
        sandboxPage.clickClickEvents();

        // Click below element
        WebElement cat = getDriver().findElement(By.xpath("//*[text()='Cat']"));
        getDriver().findElement(with(By.tagName("button")).below(cat)).click();
        // Do some assert here

        // Click near element
        getDriver().findElement(with(By.tagName("button")).near(cat)).click();
        // Do some assert here

        // Click to the right of element
        getDriver().findElement(with(By.tagName("button")).toRightOf(cat)).click();
        // Do some assert here

        // Click above element
        WebElement cow = getDriver().findElement(By.xpath("//*[text()='Cow']"));
        getDriver().findElement(with(By.tagName("button")).above(cow)).click();
        // Do some assert here

        // Click to the left of element
        getDriver().findElement(with(By.tagName("button")).toLeftOf(cow)).click();
        // Do some assert here
    }
}
