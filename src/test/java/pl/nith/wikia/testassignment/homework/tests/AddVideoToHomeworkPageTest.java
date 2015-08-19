package pl.nith.wikia.testassignment.homework.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pl.nith.wikia.testassignment.homework.pages.WikiaHomeworkPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by NiTh4r0 on 2015-08-19.
 */
public class AddVideoToHomeworkPageTest {
    private WebDriver driver;
    private WikiaHomeworkPage wikiPage;

    @BeforeClass
    @Parameters("baseUrl")
    public void initDriver(String baseUrl) throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        wikiPage = new WikiaHomeworkPage(driver);
    }

    @BeforeClass(dependsOnMethods = {"initDriver"})
    @Parameters({"username", "password"})
    public void login(String username, String password) {
        wikiPage.moveMouseToLoginDropdown()
                .fillInLoginData(username, password)
                .submitLoginData();
    }

    @AfterClass
    public void uninitDriver() throws Exception {
        driver.quit();
    }

    @Test(groups = {"addVideo"})
    public void testUserLoggedIn() {
        // Check if the user is logged on
        Assert.assertEquals(wikiPage.getSignInLableAttribute("data-id"), "userpage");
    }
}