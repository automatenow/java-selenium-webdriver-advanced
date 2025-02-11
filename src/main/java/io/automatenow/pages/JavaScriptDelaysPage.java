package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

public class JavaScriptDelaysPage extends BasePage {
    private final By msg = By.cssSelector("div[class='entry-content'] p");
    private final By countdownTxt = By.id("delay");
    private final By startBtn = By.id("start");

    public JavaScriptDelaysPage waitForCountdownText(String text) {
        waitForElementValue(countdownTxt, text);
        return this;
    }

    public JavaScriptDelaysPage clickStart() {
        click(startBtn);
        return this;
    }
}

