package io.automatenow.tests;

import io.automatenow.core.BaseTest;
import io.automatenow.utils.CustomSoftAssert;
import org.testng.annotations.*;

/**
 * @author Marco A. Cruz
 */
public class TestSoftAssertions extends BaseTest {
    public static CustomSoftAssert softAssert = new CustomSoftAssert();

    @Test(description = "Takes a screenshot for every soft assertion failure")
    public void testSoftAssert() {
        String pageTitle = getPageTitle();
        softAssert.assertEquals(pageTitle, "Homes - automateNow", "The title is invalid");

        String greeting = homePage.getWelcomeMessage();
        softAssert.assertTrue(greeting.contains("Welcomes"), "The greeting message does not contain 'Welcome'");

        softAssert.assertAll();
    }
}