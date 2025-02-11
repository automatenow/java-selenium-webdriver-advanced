package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Marco A. Cruz
 */
public class GesturesPage extends BasePage {
    private final By map = By.cssSelector(".mk-map-node-element");

    public GesturesPage dragMap(int x, int y) {
        scrollElementIntoView(map);
        pause(2);
        dragAndDropByOffset(map, x, y);
        pause(2);
        return this;
    }

    // Experimental!
    public GesturesPage dragLogo() {
        String jquery_url = "http://code.jquery.com/jquery-1.11.2.min.js";

        Path fileName = Path.of("src/main/java/io/automatenow/drag_and_drop_helper.js");
        Path fileName2 = Path.of("src/main/java/io/automatenow/jquery_load_helper.js");
        String drag_and_drop_js;
        String load_jquery_js;

        try {
            drag_and_drop_js = Files.readString(fileName);
            load_jquery_js = Files.readString(fileName2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeAsyncScript(load_jquery_js, jquery_url);
//        executor.executeScript(drag_and_drop_js+"$('#dragMe').simulateDragDrop({dropTarget: '#div2'});");
        executor.executeScript("jQuery(function($) { " +
//                " $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); " +
                drag_and_drop_js+"$('#dragMe').simulateDragDrop({dropTarget: '#div2'});" +
                " }); ");

        return this;
    }
}
