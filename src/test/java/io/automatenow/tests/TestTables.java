package io.automatenow.tests;

import io.automatenow.core.BaseTest;
import io.automatenow.pages.TablesPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Marco A. Cruz
 */
public class TestTables extends BaseTest {

    @Test(description = "A test that shows the wrong way to make assertions in Selenium")
    public void testCountriesWrongWay() {
        sandboxPage.clickTables()
                .verifyCountryInRow(1, "China")
                .verifyCountryInRow(2, "India")
                .verifyCountryInRow(3, "United States");
    }

    @Test(description = "A test that shows the right way to make assertions in Selenium")
    public void testCountriesRightWay() {
        TablesPage tables = sandboxPage.clickTables();
        String firstCountry = tables.getCountryInRow(1);
        assertEquals(firstCountry, "China", "Unexpected country found");

        String secondCountry = tables.getCountryInRow(2);
        assertEquals(secondCountry, "India", "Unexpected country found");

        String thirdCountry = tables.getCountryInRow(3);
        assertEquals(thirdCountry, "United States", "Unexpected country found");
    }
}
