package base;

import locators.*;
import util.Constants;
import util.reporter.Reporter;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected WebElement getElement(Locator locator, Object... args) {
        By by = locator.get(args);
        return BaseTest.getDriver().findElement(by);
    }
    protected List<WebElement> getElements(Locator locator, Object... args) {
        By by = locator.get(args);
        return BaseTest.getDriver().findElements(by);
    }

    /*
     * Input fields and textareas
     */
    protected void type(String message, String value, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement inputElement = getElement(locator, args);
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    protected void click(String message, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement element = getElement(locator, args);
        element.click();
    }

    protected boolean isElementVisible(Locator locator, Object... args) {
        return isElementVisibleWithWait(0, locator, args);
    }

    protected boolean isElementVisibleWithWait(int waitInSeconds, Locator locator, Object... args) {
        By by = locator.get(args);
        BaseTest.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), waitInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable th) {
            BaseTest.getDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            return false;
        }
        BaseTest.getDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return true;
    }

    protected void waitForElementToBeClickable(Locator locator, Object... args) {
        By by = locator.get(args);
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Constants.ELEMENT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(by));

        //wait until element will be at the same place (for moving elements: for Chrome and IE)
        Point currLocation, newLocation;
        long startTime = System.currentTimeMillis();
        long delta;

        newLocation = new Point(-1, -1);
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }   //the element may move veeery slowly. It's better to wait some time
            currLocation = newLocation;
            newLocation = getElement(locator, args).getLocation();
            delta = System.currentTimeMillis() - startTime;
        } while ((currLocation.getX() - newLocation.getX() != 0 && currLocation.getY() - newLocation.getY() != 0)
                && (delta <= Constants.ELEMENT_TIMEOUT_SECONDS * 1000));

        if (delta > Constants.ELEMENT_TIMEOUT_SECONDS * 1000) {
            throw new InvalidElementStateException("Element did not stand at the same place for " + Constants.ELEMENT_TIMEOUT_SECONDS + " seconds");
        }
        if (System.getProperty("browser", "firefox").equalsIgnoreCase("firefox")) {
            wait(Constants.ELEMENT_MICRO_TIMEOUT_SECONDS);
        }
    }

    protected void waitForElementVisibility(Locator locator, Object... args) {
        By by = locator.get(args);
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Constants.ELEMENT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void wait(int waitInSeconds) {
        try {
            Thread.sleep(waitInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


