package io.automatenow.tests;

import io.automatenow.core.BaseTest;
import io.automatenow.pages.*;
import io.automatenow.utils.DataUtil;
import io.automatenow.utils.TestListener;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/**
 * @author Marco A. Cruz
 */
@Listeners(TestListener.class)
public class TestSandbox extends BaseTest {
    @Test(description = "Verify the page title")
    public void testPageTitle() {
        String title = getPageTitle();
        assertTrue(title.contains("Automation"), "Page title did not match");
    }

    @Test(description = "Enters text in an input field")
    public void testEnterText() {
        String myText = "hello";

        sandboxPage.clickFormFields().setInputFieldText(myText);
        String displayedText = formFieldsPage.getInputFieldText();
        assertEquals(displayedText, myText, "Unable to verify entered text");
    }

    @Test(description = "Checks a checkbox")
    public void testCheckbox() {
        FormFieldsPage formFields = sandboxPage.clickFormFields()
                .selectCheckbox("Wine");
        assertTrue(formFields.checkboxIsSelected("Wine"), "Checkbox is not selected");

        // Negative test!
        assertFalse(formFields.checkboxIsSelected("Milk"), "Checkbox is selected");
    }

    @Test(description = "Selects from a drop-down")
    public void testSelectFromDropdown() {
        String myOption = "Yes";

        FormFieldsPage formFields = sandboxPage.clickFormFields()
                .selectFromDropdown(myOption);
        assertEquals(formFields.getDropdownText(), myOption, "Dropdown option not selected");
    }

    @Test(description = "Selects radio buttons")
    public void testSelectRadioButton() {
        String radio = "Red";
        String radio2 = "Blue";

        FormFieldsPage formFields = sandboxPage.clickFormFields()
                .selectRadioButton(radio);
        assertTrue(formFields.radioButtonIsSelected(radio), "White option was not selected");

        formFields.selectRadioButton(radio2);
        assertTrue(formFields.radioButtonIsSelected(radio2), "Blue option was not selected");

        // Negative test!
        assertFalse(formFields.radioButtonIsSelected(radio), "White option was selected");
    }

    @Test(description = "Finds the price of an item in a table")
    public void testItemPrice() {
        TablesPage tables = sandboxPage.clickTables();
        String itemPrice = tables.getItemPrice("Laptop");
        assertEquals(itemPrice, "$1200.00", "Laptop price was incorrect");

        itemPrice = tables.getItemPrice("Oranges");
        assertEquals(itemPrice, "$3.99", "Oranges price was incorrect");
    }

    @Test(description = "Selects a date from a date picker")
    public void testSelectFromDatePicker() {
        CalendarsPage calendars = sandboxPage.clickCalendars()
                .setDate("July", "4", "2030");
        String date = calendars.getDate();
        assertEquals(date, "2030-07-04", "The date was not properly set");
    }

    @Test(description = "Working with multiple open windows")
    public void testMultipleOpenWindows() {
        sandboxPage.clickWindowOperations()
                .clickNewWindow();
        sandboxPage.switchToNewWindow();
        assertTrue(waitForPageTitle("automateNow | The Best FREE Software Online Training Platform"),
                "The new window's tile does not match");
    }

    @Test(description = "Closes a second open window")
    public void testCloseSecondWindow() {
        sandboxPage.clickWindowOperations()
                .clickNewWindow();
        sandboxPage.switchToNewWindow();
        closeWindow();
        int numberOfOpenWindows = getNumberOfOpenWindows();
        assertEquals(numberOfOpenWindows, 1, "Found more than one open window");
    }

    @Test(description = "Working with multiple tabs")
    public void testMultipleTabs() {
        openNewTab();
        sandboxPage.switchToNewWindow();
        goToUrl("https://www.spacex.com");
        assertTrue(waitForPageTitle("SpaceX"), "The page title for the new window did not match");
        closeWindow();
        int numberOfOpenWindows = getNumberOfOpenWindows();
        assertEquals(numberOfOpenWindows, 1, "Found more than one open window");
    }

    @Test(description = "Test click and drag operation on a map")
    public void testClickAndDrag() {
        int x_coordinate = -300;
        int y_coordinate = 100;

        GesturesPage gestures = sandboxPage.clickGestures()
                .dragMap(x_coordinate, y_coordinate);
//        gestures.dragLogo();  // Fun one for you to try ;)
    }

    @Test(description = "Tests a JavaScript alert and a confirmation box")
    public void testPopups() {
        PopupsPage popups = sandboxPage.clickPopups()
                .clickAlertPopup();
        dismissPopup();

        popups.clickConfirmPopup()
                .acceptPopup();
        String selectionResult = popups.getConfirmPopupSelection();
        assertEquals(selectionResult, "OK it is!", "The popup selection result does not match");
    }

    @Test(description = "Tests a JavaScript prompt box")
    public void testPromptPopup() {
        String name = "Marco";

        PopupsPage popups = sandboxPage.clickPopups()
                .clickPromptPopup();
        setAlertText(name);
        acceptPopup();
        popups.waitForPromptPopupResult(String.format("Nice to meet you, %s!", name));
    }

    @Test(description = "Tests a JavaScript countdown timer")
    public void testCountdownTimer() {
        sandboxPage.clickJavaScriptDelays()
                .clickStart()
                .waitForCountdownText("Liftoff!");
    }

    @Test(description = "Tests a JavaScript modal")
    public void testModal() {
        String name = "Marco";
        String email = "info@automatenow.io";
        String message = "Test Message";

        sandboxPage.clickModals()
                .openModal()
                .modalSendMessage(name, email, message);
    }

    @Test(description = "Test mouse over")
    public void testHovering() {
        HoverPage hover = sandboxPage.clickHover()
                .hover();
        String hoverText = hover.getHoverText();
        assertEquals(hoverText, "You did it!", "Hover text did not match expected value");
    }

    @Test(description = "Tests scrolling an element into view")
    public void testScrollElementIntoView() {
        sandboxPage.scrollAdsButtonIntoView();
    }

    @Test(description = "Tests scrolling a webpage")
    public void testScrollPage() {
        // Scroll page down
        scrollPage(0, 500);
        pause(1);

        // Scroll page up
        scrollPage(0, -500);
        pause(1);
    }

    @Test(description = "Takes a page screenshot")
    public void testPageScreenshot() {
        takeScreenshot();
    }

    @Test(description = "Takes a screenshot of an element")
    public void testElementScreenshot() {
        sandboxPage.screenshotModalsButton();
    }

    @Test(description = "Uploads a file")
    public void testFileUpload() {
        sandboxPage.clickFileUpload()
                .uploadFile("C:/Users/<path_to_your_file>");  // Change the path to a file on your computer
    }

    @Test(description = "Downloads a file")
    public void testFileDownload() {
        sandboxPage.clickFileDownload()
                .downloadPDF();
    }

    @Test(description = "Works with iframes")
    public void testIframes() {
        IframesPage iframes = sandboxPage.clickIframes();
        switchFrames(0);
        iframes.search("locators");
        switchToDefaultFrame();
        String pageHeading = iframes.getPageHeading();
        assertEquals(pageHeading, "Iframes", "Page heading does not match.");
    }

    @Test(description = "Tests table pagination")
    public void testTablePagination() {
        TablesPage tables = sandboxPage.clickTables()
                .sortByCountry();
        String populationUK = tables.getPopulation("United Kingdom");
        assertNotEquals(populationUK, "-1", "The country was not found on the list!");
        log.info("The population for the United Kingdom is " + populationUK + " million.");
    }

    @Test(description = "Tests setting/clearing cookies")
    public void testSetAndClearCookies() {
        String cookieName = properties.getProperty("cookiePolicy");
        setCookie(cookieName, "yes");
        Cookie myCookie = getCookie(cookieName);
        assertEquals(myCookie.getName(), cookieName, "Cookie not properly set.");
        clearCookies();
        myCookie = getCookie(cookieName);
        assertNull(myCookie, "Cookies have not been cleared.");
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "dataProvider1")
    public void testSubmitForm(HashMap<String, String> testData) {
        sandboxPage.clickFormFields()
                .setInputFieldText(testData.get("Input Field"))
                .selectCheckbox(testData.get("Checkbox"))
                .selectRadioButton(testData.get("Radio Button"))
                .selectFromDropdown(testData.get("Dropdown"))
                .setEmail(testData.get("Email"))
                .setMessage(testData.get("Message"))
                .clickSubmit();

        dismissPopup();
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "dataProvider2")
    public void testVerifyTableItems(HashMap<String, String> testData) {
        TablesPage tables = sandboxPage.clickTables();

        String price = tables.getItemPrice("Oranges");
        assertEquals(price, testData.get("Oranges"), "Price mismatch");

        price = tables.getItemPrice("Laptop");
        assertEquals(price, testData.get("Laptop"), "Price mismatch");

        price = tables.getItemPrice("Marbles");
        assertEquals(price, testData.get("Marbles"), "Price mismatch");
    }
}
