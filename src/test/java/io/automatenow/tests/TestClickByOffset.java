package io.automatenow.tests;

import io.automatenow.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 * @author Marco A. Cruz
 */
public class TestClickByOffset extends BaseTest {

    @Test(description = "Clicks on a slider by offset")
    public void testClickByOffset() {
        sandboxPage.clickSlider();

        WebElement slider = getDriver().findElement(By.id("slideMe"));
        slider.click();

        Actions actions = new Actions(getDriver());

        // Click right side of slider
//        actions.moveToElement(slider, 250, 0).click().build().perform();

        // Click right side of slider using the element's width
        int width = slider.getSize().getWidth();
//        actions.moveToElement(slider, (width/2) - 100, 0).click().build().perform();

        // Click left side of slider
        actions.moveToElement(slider, -250, 0).click().build().perform();
    }
}
