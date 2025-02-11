package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

/**
 * @author Marco A. Cruz
 */
public class ModalsPage extends BasePage {
    private final By formModal = By.id("formModal");
    private final By modalName = By.id("g1051-name");
    private final By modalEmail = By.id("g1051-email");
    private final By modalMsg = By.id("contact-form-comment-g1051-message");
    private final By modalSubmitBtn = By.cssSelector("div[class='pum-content popmake-content'] button[type='submit']");

    public ModalsPage openModal() {
        click(formModal);
        return this;
    }

    public ModalsPage modalSendMessage(String name, String email, String message) {
        setText(modalName, name);
        setText(modalEmail, email);
        setText(modalMsg, message);
        click(modalSubmitBtn);
        return this;
    }
}
