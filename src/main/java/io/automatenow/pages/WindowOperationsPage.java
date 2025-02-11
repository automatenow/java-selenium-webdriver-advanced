package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

public class WindowOperationsPage extends BasePage {
    private final By newWindowBtn = By.xpath("//*[text()='New Window']");

    public void clickNewWindow() {
        click(newWindowBtn);
    }
}
