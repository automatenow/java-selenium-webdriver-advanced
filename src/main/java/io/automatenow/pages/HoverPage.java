package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

/**
 * @author Marco A. Cruz
 */
public class HoverPage extends BasePage {
    private final By hover = By.id("mouse_over");

    public HoverPage hover() {
        hoverOverElement(hover);
        return this;
    }

    public String getHoverText() {
        return getText(hover);
    }
}
