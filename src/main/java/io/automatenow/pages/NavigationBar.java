package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

/**
 * @author Marco A. Cruz
 */
public class NavigationBar extends BasePage {
    private final By acceptCookiesBtn = By.id("cookie_action_close_header");


    public NavigationBar acceptCookies() {
        click(acceptCookiesBtn);
        return this;
    }
}
