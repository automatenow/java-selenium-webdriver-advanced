package io.automatenow.utils;

import io.automatenow.core.BasePage;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

/**
 * @author Marco A. Cruz
 */
public class CustomSoftAssert extends SoftAssert {

    @Override
    public void onAssertFailure(IAssert<?> var1, AssertionError var2) {
        new BasePage().takeScreenshot();
    }
}
