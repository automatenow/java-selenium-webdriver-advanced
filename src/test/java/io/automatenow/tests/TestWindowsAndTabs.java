package io.automatenow.tests;

import io.automatenow.core.BaseTest;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

/**
 * @author Marco A. Cruz
 */
public class TestWindowsAndTabs extends BaseTest {

    @Test(description = "Opens a new browser window")
    public void testOpenNewWindow() {
        sandboxPage.clickWindowOperations();
        getDriver().switchTo().newWindow(WindowType.WINDOW);
        getDriver().get("https://www.google.com");
    }

    @Test(description = "Opens a new browser tab")
    public void testOpenNewTab() {
        getDriver().switchTo().newWindow(WindowType.TAB);
        getDriver().get("https://www.google.com");
    }
}
