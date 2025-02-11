package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

/**
 * @author Marco A. Cruz
 */
public class IframesPage extends BasePage {
    private final By searchBox = By.cssSelector("[aria-label='Search (Ctrl+K)']");
    private final By pageHeading = By.cssSelector("h1");

    public IframesPage search(String searchQuery) {
        click(searchBox);
        // Do more things here...
        return this;
    }

    public String getPageHeading() {
        return getText(pageHeading);
    }
}
