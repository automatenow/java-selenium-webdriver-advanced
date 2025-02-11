package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Marco A. Cruz
 */
public class FormFieldsPage extends BasePage {
    private final By inputField = By.id("name-input");
    private final By dropDown = By.id("automation");
    private final By emailField = By.id("email");
    private final By messageField = By.id("message");
    private final By submitBtn = By.id("submit-btn");

    public FormFieldsPage setInputFieldText(String text) {
        setText(inputField, text);
        return this;
    }

    public String getInputFieldText() {
        return getText(inputField);
    }

    /**
     * Selects a check box
     *
     * @param option Range is 1 to 3
     */
    public FormFieldsPage selectCheckbox(String option) {
        click(By.xpath("//input[@value='" + option + "']"));
        return this;
    }

    public boolean checkboxIsSelected(String option) {
        return getDriver().findElement(By.xpath("//input[@value='" + option + "']")).isSelected();
    }

    /**
     * Selects drop-down option
     *
     * @param option Displayed text
     */
    public FormFieldsPage selectFromDropdown(String option) {
        Select dDown = new Select(getDriver().findElement(dropDown));
        dDown.selectByVisibleText(option);
        return this;
    }

    /**
     * @return The option that is currently selected.
     */
    public String getDropdownText() {
        Select dDown = new Select(getDriver().findElement(dropDown));
        return dDown.getFirstSelectedOption().getText();
    }

    /**
     * Selects a radio button
     *
     * @param option Case sensitive value
     */
    public FormFieldsPage selectRadioButton(String option) {
        click(By.cssSelector("input[value='" + option + "']"));
        return this;
    }

    public boolean radioButtonIsSelected(String option) {
        return getDriver().findElement(By.cssSelector("input[value='" + option + "']")).isSelected();
    }

    public FormFieldsPage setEmail(String email) {
        setText(emailField, email);
        return this;
    }

    public FormFieldsPage setMessage(String message) {
        setText(messageField, message);
        return this;
    }

    public FormFieldsPage clickSubmit() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scrollElementIntoView(submitBtn);
        click(submitBtn);
        return this;
    }
}
