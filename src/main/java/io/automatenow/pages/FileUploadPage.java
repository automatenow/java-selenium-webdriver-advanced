package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Marco A. Cruz
 */
public class FileUploadPage extends BasePage {
    private final By chooseFileBtn = By.id("file-upload");
    private final By uploadBtn = By.id("upload-btn");
    private final By uploadStatus = By.cssSelector(".wpcf7-response-output");
    
    public FileUploadPage uploadFile(String filepath) {
        // Ensure file exists
        if (!Files.exists(Paths.get(filepath))) {
            throw new IllegalArgumentException("File not found: " + filepath);
        }

        getDriver().findElement(chooseFileBtn).sendKeys(filepath);
        click(uploadBtn);
        waitForElementText(uploadStatus, "File upload complete");
        return this;
    }
}
