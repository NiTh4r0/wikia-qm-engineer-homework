import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by NiTh4r0 on 2015-08-18.
 */
public class WikiaHomeworkPage {
    private WebDriver driver;
    private WebElement AccountNavigation;
    private WebElement UserLoginDropdown;
    private WebElement searchInput;
    private WebElement usernameInput;
    private WebElement passwordInput;
    @FindBy(how = How.CLASS_NAME, using = "login-button")
    private WebElement loginButton;
    @FindBy(how = How.CLASS_NAME, using = "ajaxLogin")
    private WebElement singInLabel;

    public WikiaHomeworkPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean getLoginDropdownVisibility() {
        return UserLoginDropdown.isDisplayed();
    }

    public WikiaHomeworkPage moveMouseToLoginDropdown() {
        Actions mouse = new Actions(driver);
        mouse.moveToElement(AccountNavigation).build().perform();

        waitForElement(UserLoginDropdown);

        return this;
    }

    public WikiaHomeworkPage fillInLoginData(String username, String password) {
        waitForElement(usernameInput);

        // Clear the username and password input boxes just in case
        usernameInput.clear();
        passwordInput.clear();
        // Fill in the input fields with data
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        return this;
    }

    public WikiaHomeworkPage submitLoginData() {
        waitForElement(loginButton);
        loginButton.submit();

        return this;
    }

    public String getSignInLableAttribute(String attrName) {
        return singInLabel.getAttribute(attrName);
    }

    private void waitForElement(WebElement element) {
        if (!element.isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }
}
