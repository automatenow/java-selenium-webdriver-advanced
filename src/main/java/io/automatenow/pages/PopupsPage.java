package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

/**
 * @author Marco A. Cruz
 */
public class PopupsPage extends BasePage {
    private final By alertBtn = By.id("alert");
    private final By confirmBtn = By.id("confirm");
    private final By confirmPopupResult = By.id("confirmResult");
    private final By promptBtn = By.id("prompt");
    private final By promptPopupResult = By.id("promptResult");

    public PopupsPage clickAlertPopup() {
        click(alertBtn);
        return this;
    }

    public PopupsPage clickConfirmPopup() {
        click(confirmBtn);
        return this;
    }

    public String getConfirmPopupSelection() {
        return getText(confirmPopupResult);
    }

    public PopupsPage clickPromptPopup() {
        click(promptBtn);
        return this;
    }

    public void waitForPromptPopupResult(String expectedText) {
        waitForElementText(promptPopupResult, expectedText);
    }
}
