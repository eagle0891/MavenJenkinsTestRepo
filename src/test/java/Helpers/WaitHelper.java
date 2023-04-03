package Helpers;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class WaitHelper {

    @Getter
    protected WebDriverWait wait;
    @Getter
    protected WebDriver webDriver;

    protected static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);

    public WebElement waitForExpectedElement(final By by, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), timeout);
        try {
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException | TimeoutException e) {
            LOG.info(e.getMessage());
            return null;
        } catch (StaleElementReferenceException e) {
            LOG.info(e.getMessage());
            return wait.until(visibilityOfElementLocated(by));
        }
    }

    ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return driver -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOG.error("Error Message : " + e.getMessage());
            }
            WebElement element = getWebDriver().findElement(by);
            return element.isDisplayed() ? element : null;
        };
    }

    public void isElementDisplayed(By bySelector) {
        boolean expectedElementDisplayed = false;
        try {
            //highlightElement(bySelector,1);
            expectedElementDisplayed = waitForExpectedElement(bySelector, Duration.ofMillis(50)).isDisplayed();
        } catch (Exception e) {
            LOG.info("\nPresence of Element : " + expectedElementDisplayed);
        }

        assertTrue("\n\n ******** ERROR *********** \n" +
                        "\n EXPECTED ELEMENT NOT DISPLAYED!! " +
                        "\n Element NOT Found : " + bySelector +"\n",
                expectedElementDisplayed);
    }

    public static boolean waitForElementToDisplay(WebElement element, int timeOutInSeconds) throws InterruptedException {
        boolean isDisplayed = false;
        for(int i = 0; i < timeOutInSeconds; i++) {
            try {
                if (element.isDisplayed()) {
                    System.out.println(element.toString() + " is visible");
                    isDisplayed = true;
                    break;
                }
            } catch (NoSuchElementException exception) {
                System.out.println(element.toString() + " is not visible");
                Thread.sleep(10);
            }
        }
        return isDisplayed;
    }

    public void waitForElementToBeClickable(WebDriver driver, WebElement locator, Duration timeout){
        new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
