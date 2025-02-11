package io.automatenow.tests;

import io.automatenow.core.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Marco A. Cruz
 */
public class TestHomepage extends BaseTest {

    @Test(groups = "firefox", description = "test description for testLogo1")
    public void testLogo1() {
        boolean logoIsPresent = homePage.logoIsDisplayed();
        assertFalse(logoIsPresent, "The automateNow logo is displayed");
    }

    @Test(groups = "chrome")
    public void testLogo2() {
        boolean logoIsPresent = homePage.logoIsDisplayed();
        assertFalse(logoIsPresent, "The automateNow logo is  displayed");
    }

    @Test(groups = "chrome", description = "test description for testLogo3")
    public void testLogo3() {
        boolean logoIsPresent = homePage.logoIsDisplayed();
        assertTrue(logoIsPresent, "The automateNow logo is displayed");
    }
}
