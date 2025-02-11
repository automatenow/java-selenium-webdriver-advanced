package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

/**
 * @author Marco A. Cruz
 */
public class HomePage extends BasePage {
    private final By welcomeMsg = By.xpath("//*[normalize-space()='Welcome to your software automation practice website!']");
    private final By logo = By.xpath("//img[@alt='automateNow Logo']");

    public String getWelcomeMessage() {
        return getDriver().findElement(welcomeMsg).getText();
    }

    public boolean logoIsDisplayed() {
        return getDriver().findElement(logo).isDisplayed();
    }
}
