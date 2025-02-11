package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Marco A. Cruz
 */
public class TablesPage extends BasePage {
    private final By countrySort = By.cssSelector("[aria-label='Country: Activate to sort']");
    private final By noNextBtn = By.cssSelector(".paginate_button.next.disabled");
    private final By nextBtn = By.cssSelector("[aria-label='Next']");
    private final By pageHeading = By.cssSelector("h1");

    public TablesPage() {
        // This should be one of the few type of assertions that are made in a page object class
        String h1Heading = getText(pageHeading);
        assertEquals(h1Heading, "Tables");
    }

    public TablesPage verifyCountryInRow(int row, String expectedCountry) {
        String actualCountry = getText(By.xpath("//table[@id='tablepress-1']/tbody/tr[" + row + "]/td[2]"));

        // This is wrong! Asserts of this nature should not be in a page object class.
        assertEquals(actualCountry, expectedCountry, "Unexpected country found");

        return this;
    }

    public String getCountryInRow(int row) {
        return getText(By.xpath("//table[@id='tablepress-1']/tbody/tr[" + row + "]/td[2]"));
    }

    public String getItemPrice(String item) {
        return getDriver().findElement(By.xpath("//td[text()='" + item + "']/following-sibling::td")).getText();
    }

    public TablesPage sortByCountry() {
        scrollElementIntoView(countrySort);
        click(countrySort);
        return this;
    }

    /**
     * Gets a country's population
     *
     * @param country The name of the country
     * @return Population in millions when country is found; -1 otherwise.
     */
    public String getPopulation(String country) {
        boolean foundCountry = false;

        while (!foundCountry) {
            List<WebElement> countryListedOnCurrentPage = getDriver().findElements(By.xpath("//table[@id='tablepress-1']//td[normalize-space()='"+ country +"']"));
            List<WebElement> disabledNextBtn = getDriver().findElements(noNextBtn);

            if (countryListedOnCurrentPage.size() > 0) {
                foundCountry = true;
            } else if (disabledNextBtn.size() == 0) {
                scrollElementIntoView(nextBtn);
                click(nextBtn);
            } else {
                return "-1";
            }
        }

        // Return the country's population
        return getText(By.xpath("//td[normalize-space()='" + country + "']/following-sibling::td"));
    }
}
