package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

/**
 * @author Marco A. Cruz
 */
public class FileDownloadPage extends BasePage {
    private final By pdfDownloadBtn = By.xpath("//a[@class='wpdm-download-link download-on-click btn btn-primary ']");

    public FileDownloadPage downloadPDF() {
        click(pdfDownloadBtn);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
}
