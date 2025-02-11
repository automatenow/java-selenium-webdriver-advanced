package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Marco A. Cruz
 */
public class SandboxPage extends BasePage {
    private final By adsBtn = By.xpath("//a[contains(text(),'Ads')]");
    private final By formFieldsBtn = By.xpath("//a[contains(text(),'Form Fields')]");
    private final By clickEventsBtn = By.xpath("//a[contains(text(),'Click Events')]");
    private final By tablesBtn = By.xpath("//a[contains(text(),'Tables')]");
    private final By calendarsBtn = By.xpath("//a[contains(text(),'Calendars')]");
    private final By jsDelaysBtn = By.xpath("//a[contains(text(),'JavaScript Delays')]");
    private final By gesturesBtn = By.xpath("//a[contains(text(),'Gestures')]");
    private final By popupsBtn = By.xpath("//a[contains(text(),'Popups')]");
    private final By modalsBtn = By.xpath("//a[contains(text(),'Modals')]");
    private final By hoverBtn = By.xpath("//a[contains(text(),'Hover')]");
    private final By fileUpload = By.xpath("//a[contains(text(),'File Upload')]");
    private final By fileDownload = By.xpath("//a[contains(text(),'File Download')]");
    private final By iframes = By.xpath("//a[contains(text(),'Iframes')]");
    private final By slider = By.xpath("//a[contains(text(),'Slider')]");
    private final By windowOpsBtn = By.xpath("//a[contains(text(),'Window Operations')]");

    public void switchToNewWindow() {
        // Get current window handle
        String currentWindow = getWindowHandle();

        // Get all window handles
        Set<String> handles = getWindowHandles();

        // Switch to new window
        Iterator<String> iter = handles.iterator();
        String newWindow;
        while (iter.hasNext()) {
            newWindow = iter.next();
            if (!currentWindow.equals(newWindow)) {
                getDriver().switchTo().window(newWindow);
            }
        }
    }

    public SandboxPage scrollAdsButtonIntoView() {
        scrollElementIntoView(adsBtn);
        return this;
    }

    public FormFieldsPage clickFormFields() {
        click(formFieldsBtn);
        return new FormFieldsPage();
    }

    public ClickEventsPage clickClickEvents() {
        scrollElementIntoView(clickEventsBtn);
        click(clickEventsBtn);
        return new ClickEventsPage();
    }

    public TablesPage clickTables() {
        scrollElementIntoView(tablesBtn);
        click(tablesBtn);
        return new TablesPage();
    }

    public CalendarsPage clickCalendars() {
        click(calendarsBtn);
        return new CalendarsPage();
    }

    public WindowOperationsPage clickWindowOperations() {
        scrollElementIntoView(windowOpsBtn);
        click(windowOpsBtn);
        return new WindowOperationsPage();
    }

    public GesturesPage clickGestures() {
        scrollElementIntoView(gesturesBtn);
        click(gesturesBtn);
        return new GesturesPage();
    }

    public PopupsPage clickPopups() {
        click(popupsBtn);
        return new PopupsPage();
    }

    public JavaScriptDelaysPage clickJavaScriptDelays() {
        click(jsDelaysBtn);
        return new JavaScriptDelaysPage();
    }

    public ModalsPage clickModals() {
        click(modalsBtn);
        return new ModalsPage();
    }

    public HoverPage clickHover() {
        scrollElementIntoView(hoverBtn);
        click(hoverBtn);
        return new HoverPage();
    }

    public FileUploadPage clickFileUpload() {
        scrollElementIntoView(fileUpload);
        click(fileUpload);
        return new FileUploadPage();
    }

    public FileDownloadPage clickFileDownload() {
        scrollElementIntoView(hoverBtn);
        click(fileDownload);
        return new FileDownloadPage();
    }

    public SandboxPage screenshotModalsButton() {
        takeElementScreenshot(modalsBtn);
        return this;
    }

    public IframesPage clickIframes() {
        scrollElementIntoView(iframes);
        click(iframes);
        return new IframesPage();
    }

    public void clickSlider() {
        click(slider);
    }
}
