package pl.nith.wikia.testassignment.homework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by NiTh4r0 on 2015-08-19.
 */
public abstract class WikiaBasePage {
    private WebDriver driver;

    public WikiaBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() { return driver; }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void waitForElement(WebElement element) {
        if (!element.isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }
}
