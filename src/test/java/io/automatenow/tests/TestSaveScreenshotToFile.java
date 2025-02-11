package io.automatenow.tests;

import io.automatenow.core.BaseTest;
import io.automatenow.utils.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Marco A. Cruz
 */
@Listeners(TestListener.class)
public class TestSaveScreenshotToFile extends BaseTest {

    /*
    Before running this test make sure that the appropriate onTestFailure() option is not commented out in the
    TestListener class.
     */
    @Test(groups = {"smoke"}, description = "This test fails, causing the onTestFailure() method in TestListener class" +
            " to run")
    public void testOnTestFailureMethod() {
        String pageTitle = getPageTitle();
        assertEquals(pageTitle, "aautomateNow | The place to learn software automation.", "The title is invalid");
    }
}