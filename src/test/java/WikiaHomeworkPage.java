import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by NiTh4r0 on 2015-08-18.
 */
public class WikiaHomeworkPage {

    private WebElement AccountNavigation;
    private WebElement searchInput;
    private WebElement usernameInput;
    private WebElement passwordInput;


    public void moveMouseToLoginDropdown(WebDriver driver) {

        Actions mouse = new Actions(driver);

        mouse.moveToElement(AccountNavigation).build().perform();

        WebDriverWait waitForDropdown = new WebDriverWait(driver, 3);
        waitForDropdown.until(ExpectedConditions.visibilityOf(usernameInput));

        // WebElement signInMenu = driver.findElement(By.id("AccountNavigation"));
        // mouse.moveToElement(signInMenu).build().perform();

        usernameInput.sendKeys("robert.zietek");
        passwordInput.sendKeys("88_pass_4_wikia", Keys.ENTER);
    }

    public void searchInputTest() {
        searchInput.sendKeys("test search");
        searchInput.submit();
    }
}
