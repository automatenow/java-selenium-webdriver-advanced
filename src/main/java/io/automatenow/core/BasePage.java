package io.automatenow.core;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Marco A. Cruz
 */
public class BasePage {
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static Logger log = LogManager.getLogger();

    public String baseUrl;

    public Properties properties;

    public Boolean goToHomepage(String browser) {
        try {
            loadProperties();
            openBrowser(browser);
            getDriver().get(baseUrl);
        } catch (Exception e) {
            System.out.println("Unable to navigate to the homepage");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    private synchronized void loadProperties() {
        try (FileInputStream file = new FileInputStream("src/main/java/io/automatenow/config/config.properties")) {
            properties = new Properties();
            properties.load(file);
            baseUrl = properties.getProperty("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openBrowser(String browser) {

        switch (browser.toLowerCase()) {
            case "chrome":
                // Run in headless mode
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless");
//                options.addArguments("--window-size=1920,1080");  // or: getDriver().manage().window().setSize(new Dimension(1024, 768));
//                driver.set(ThreadGuard.protect(new ChromeDriver(options)));

                // Change download default directory
//                ChromeOptions options = new ChromeOptions();
//                Map<String, Object> prefs = new HashMap<>();
                // Replace '\\' with '/' when setting the path in a Mac
//                prefs.put("download.default_directory", System.getProperty("user.dir") + "\\");
//                options.setExperimentalOption("prefs", prefs);
//                driver.set(ThreadGuard.protect(new ChromeDriver(options)));

                // Disable message 'Chrome is being controlled by automated test software'
//                ChromeOptions options = new ChromeOptions();
//                options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
//                driver.set(ThreadGuard.protect(new ChromeDriver(options)));
                driver.set(ThreadGuard.protect(new ChromeDriver()));
                break;

            case "firefox":
                driver.set(ThreadGuard.protect(new FirefoxDriver()));
                break;

            default:
                log.error("Invalid browser property: " + browser);
        }

        assert getDriver() != null;
//        getDriver().manage().window().setSize(new Dimension(1024, 768));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();
    }

    public void closeBrowser() {
        getDriver().quit();
        driver.remove();
    }

    public void closeWindow() {
        getDriver().close();
    }

    public void setText(By locator, String text) {
        getDriver().findElement(locator).clear();
        getDriver().findElement(locator).sendKeys(text);
        tab(locator);
    }

    public void tab(By locator) {
        getDriver().findElement(locator).sendKeys(Keys.TAB);
    }

    public String getText(By locator) {
        String displayedText = getDriver().findElement(locator).getText();
        if (displayedText.isEmpty()) {
            return getDriver().findElement(locator).getDomProperty("value");
        } else {
            return displayedText;
        }
    }

    public void click(By locator) {
        getDriver().findElement(locator).click();
    }

    public void goBack() {
        getDriver().navigate().back();
    }

    public String getWindowHandle() {
        return getDriver().getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        return getDriver().getWindowHandles();
    }

    public int getNumberOfOpenWindows() {
        return getDriver().getWindowHandles().size();
    }

    public void openNewTab() {
        ((JavascriptExecutor) getDriver()).executeScript("window.open()");
    }

    public void goToUrl(String url) {
        getDriver().get(url);
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public boolean waitForPageTitle(String title) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.titleContains(title));
    }

    /**
     * Performs a drag-n-drop operation on a given element by a given x,y offset.
     *
     * @param locator The element to be interacted with
     * @param x       x-coordinate
     * @param y       Y-coordinate
     */
    public void dragAndDropByOffset(By locator, int x, int y) {
        WebElement element = getDriver().findElement(locator);
        new Actions(getDriver()).dragAndDropBy(element, x, y).perform();
    }

    public void dismissPopup() {
        getDriver().switchTo().alert().dismiss();
    }

    public void acceptPopup() {
        getDriver().switchTo().alert().accept();
    }

    public void setAlertText(String text) {
        getDriver().switchTo().alert().sendKeys(text);
    }

    public void waitForElementText(By locator, String text) {
        // This is an explicit wait
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(locator, text));

        // This is a FluentWait. It does the same as the above wait, but it is more customizable
//        Wait<WebDriver> wait = new FluentWait<>(getDriver())
//                .withTimeout(Duration.ofSeconds(3))
//                .pollingEvery(Duration.ofMillis(250))
//                .ignoring(NoSuchElementException.class);
//        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    public void waitForElementValue(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(12));
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    public void hoverOverElement(By locator) {
        WebElement element = getDriver().findElement(locator);
        new Actions(getDriver()).moveToElement(element).perform();
    }

    /**
     * Scroll the specified element into the center of the viewport
     * @param locator The element to scroll into view
     */
    public void scrollElementIntoView(By locator) {
        WebElement element = getDriver().findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].scrollIntoView({block: \"center\"});", element);
        pause(1);
    }

    /**
     * Pauses the test execution for the specified number of seconds.
     * @param seconds The number of seconds to pause.
     */
    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scrolls the document by the specified number of pixels.
     *
     * @param x How many pixels to scroll by, along the x-axis (horizontal).
     * @param y How many pixels to scroll by, along the y-axis (vertical).
     */
    public void scrollPage(int x, int y) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    /**
     * Takes screenshot of whole page and uses the current date/time as the file name
     */
    public void takeScreenshot() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss-SSS");
        LocalDateTime dateTime = LocalDateTime.now();
        takeScreenshot(dateTime.format(formatter));
    }

    /**
     * Takes screenshot of whole page and allows you to name the screenshot
     *
     * @param screenshotName The screenshot file name
     */
    public void takeScreenshot(String screenshotName) {
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./failed_tests/" + screenshotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes screenshot of single WebElement
     */
    public void takeElementScreenshot(By locator) {
        WebElement element = getDriver().findElement(locator);
        File file = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param frame The index of the frame to switch to (first frame has index 0)
     */
    public void switchFrames(int frame) {
        getDriver().switchTo().frame(frame);
    }

    public void switchToDefaultFrame() {
        getDriver().switchTo().defaultContent();
    }

    public void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        getDriver().manage().addCookie(cookie);
    }

    public Cookie getCookie(String name) {
        return getDriver().manage().getCookieNamed(name);
    }

    public void clearCookies() {
        getDriver().manage().deleteAllCookies();
        refresh();
    }

    private void refresh() {
        getDriver().navigate().refresh();
    }
}
