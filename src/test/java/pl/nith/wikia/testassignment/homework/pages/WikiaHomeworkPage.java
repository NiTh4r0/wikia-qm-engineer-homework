package pl.nith.wikia.testassignment.homework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.nith.wikia.testassignment.homework.helpers.WebElementHelper;

/**
 * Created by NiTh4r0 on 2015-08-18.
 */
public class WikiaHomeworkPage extends WikiaBasePage {
    private WebElement AccountNavigation;
    private WebElement UserLoginDropdown;
    private WebElement searchInput;
    private WebElement usernameInput;
    private WebElement passwordInput;
    @FindBy(how = How.CLASS_NAME, using = "login-button")
    private WebElement loginButton;
    @FindBy(how = How.CLASS_NAME, using = "ajaxLogin")
    private WebElement singInLabel;
    @FindBy(how = How.CLASS_NAME, using = "contribute")
    private WebElement contributeDropdown;
    @FindBy(how = How.CLASS_NAME, using = "WikiaMenuElement")
    private WebElement WikiaMenuElement;
    private WebElement addVideoLink;

    public WikiaHomeworkPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean getLoginDropdownVisibility() {
        return UserLoginDropdown.isDisplayed();
    }
    public boolean getContributeDropdownVisibility() { return WikiaMenuElement.isDisplayed(); }

    public WikiaHomeworkPage moveMouseToLoginDropdown() {
        Actions mouse = new Actions(getDriver());
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

    public WikiaHomeworkPage clickContributeButton() {
        contributeDropdown.click();

        return this;
    }

    public WikiaAddVideoPage clickAddVideoLink() throws NullPointerException {
        waitForElement(WikiaMenuElement);

        addVideoLink = WebElementHelper.findLinkElementByHref(getDriver(), "WikiaVideoAdd");

        if (addVideoLink != null)
            addVideoLink.click();
        else throw new NullPointerException();

        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.id("wpWikiaVideoAddUrl")));

        return new WikiaAddVideoPage(getDriver());
    }
}
