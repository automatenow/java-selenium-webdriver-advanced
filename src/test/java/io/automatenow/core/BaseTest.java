package io.automatenow.core;

import io.automatenow.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * @author Marco A. Cruz
 */
public class BaseTest extends BasePage {
    protected CalendarsPage calendarsPage;
    protected FormFieldsPage formFieldsPage;
    protected GesturesPage gesturesPage;
    protected HoverPage hoverPage;
    protected ModalsPage modalsPage;
    protected PopupsPage popupsPage;
    protected NavigationBar navBar;
    protected HomePage homePage;
    protected SandboxPage sandboxPage;
    protected WindowOperationsPage windowOperationsPage;
    protected ClickEventsPage clickEventsPage;

    /**
     * This method runs before the suite starts, and it initializes all the page objects.
     */
    @BeforeSuite
    public void suiteSetup() {
        calendarsPage = new CalendarsPage();
        formFieldsPage = new FormFieldsPage();
        gesturesPage = new GesturesPage();
        hoverPage = new HoverPage();
        modalsPage = new ModalsPage();
        popupsPage = new PopupsPage();
        navBar = new NavigationBar();
        homePage = new HomePage();
        sandboxPage = new SandboxPage();
        windowOperationsPage = new WindowOperationsPage();
        clickEventsPage = new ClickEventsPage();
    }

    /**
     * This method runs before every test (including during parallel execution).
     * It expects a 'browser' parameter, which is passed through from the testng.xml file. The default browser to be
     * used is Chrome.
     *
     * @param browser The web browser on which tests should be executed.
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        Assert.assertTrue(goToHomepage(browser), "An error occurred while navigating to the homepage");
    }

    /**
     * This method runs after every test (including during parallel execution).
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeBrowser();
    }
}
