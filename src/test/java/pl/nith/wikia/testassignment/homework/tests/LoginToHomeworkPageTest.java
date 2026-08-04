package pl.nith.wikia.testassignment.homework.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.nith.wikia.testassignment.homework.pages.WikiaHomeworkPage;

/**
 * Created by NiTh4r0 on 2015-08-18.
 */
public class LoginToHomeworkPageTest {

  private WebDriver driver;
  private WikiaHomeworkPage wikiPage;

  @BeforeClass
  @Parameters({"baseUrl", "useChrome"})
  public void initDriver(String baseUrl, String useChrome) {
    if (useChrome.equals("true")) {
      driver = new ChromeDriver();
    } else {
      driver = new FirefoxDriver();
    }
    driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
    driver.get(baseUrl);
    wikiPage = new WikiaHomeworkPage(driver);
  }

  @AfterClass
  public void uninitDriver() {
    driver.quit();
  }

  @Test(groups = {"login"})
  @Parameters({"baseUrl"})
  public void testCorrectPage(String baseUrl) {
    // Check if the current page is the right one
    assertEquals(wikiPage.getCurrentUrl(), baseUrl);
  }

  @Test(groups = {"login"})
  public void testUserLoggedOut() {
    // Check if the user is logged out
    assertEquals(wikiPage.getSignInLableAttribute("data-id"), "login");
  }

  @Test(groups = {"login"})
  public void testVisibilityOfDropdownLogin() {
    // Move the mouse to the login dropdown and check if it shows up
    wikiPage.moveMouseToLoginDropdown();
    assertTrue(wikiPage.getLoginDropdownVisibility());
  }

  @Parameters({"username", "password"})
  @Test(groups = {"login"}, dependsOnMethods = {"testVisibilityOfDropdownLogin"})
  public void testUserLoggedIn(String username, String password) {
    // Fill in the form with login credentials, submit them and check if the user is logged in
    wikiPage.fillInLoginData(username, password)
        .submitLoginData();
    assertEquals(wikiPage.getSignInLableAttribute("data-id"), "userpage");
  }
}
