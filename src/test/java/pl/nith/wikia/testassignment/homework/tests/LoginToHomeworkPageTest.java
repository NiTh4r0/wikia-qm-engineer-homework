package pl.nith.wikia.testassignment.homework.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pl.nith.wikia.testassignment.homework.pages.WikiaHomeworkPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by NiTh4r0 on 2015-08-18.
 */
public class LoginToHomeworkPageTest {
    private WebDriver driver;
    private WikiaHomeworkPage wikiPage;

    @Parameters("baseUrl")
    @BeforeClass
    public void initDriver(String baseUrl) throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        wikiPage = new WikiaHomeworkPage(driver);
    }

    @AfterClass
    public void uninitDriver() throws Exception {
        driver.quit();
    }

    @Test(groups = {"login"})
    @Parameters({"baseUrl"})
    public void testCorrectPage(String baseUrl) {
        // Check if the current page is the right one
        Assert.assertEquals(wikiPage.getCurrentUrl(), baseUrl);
    }

    @Test(groups = {"login"})
    public void testUserLoggedOut() {
        // Check if the user is logged out
        Assert.assertEquals(wikiPage.getSignInLableAttribute("data-id"), "login");
    }

    @Test(groups = {"login"})
    public void testVisibilityOfDropdownLogin() {
        // Move the mouse to the login dropdown and check if it shows up
        wikiPage.moveMouseToLoginDropdown();
        Assert.assertTrue(wikiPage.getLoginDropdownVisibility());
    }

    @Parameters({"username", "password"})
    @Test(groups = {"login"}, dependsOnMethods = {"testVisibilityOfDropdownLogin"})
    public void testUserLoggedIn(String username, String password) {
        // Fill in the form with login credentials, submit them and check if the user is logged in
        wikiPage.fillInLoginData(username, password)
                .submitLoginData();
        Assert.assertEquals(wikiPage.getSignInLableAttribute("data-id"), "userpage");
    }
}
