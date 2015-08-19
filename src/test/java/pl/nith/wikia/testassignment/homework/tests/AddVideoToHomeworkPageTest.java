package pl.nith.wikia.testassignment.homework.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pl.nith.wikia.testassignment.homework.helpers.WebElementHelper;
import pl.nith.wikia.testassignment.homework.pages.WikiaAddVideoPage;
import pl.nith.wikia.testassignment.homework.pages.WikiaFilePage;
import pl.nith.wikia.testassignment.homework.pages.WikiaFlashElementPage;
import pl.nith.wikia.testassignment.homework.pages.WikiaHomeworkPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by NiTh4r0 on 2015-08-19.
 */
public class AddVideoToHomeworkPageTest {
    private WebDriver driver;
    private WikiaHomeworkPage wikiPage;

    @BeforeClass
    @Parameters({"baseUrl", "useChrome"})
    public void initDriver(String baseUrl, String useChrome) throws Exception {
        if (useChrome.equals("true"))
            driver = new ChromeDriver();
        else
            driver = new FirefoxDriver();
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
        assertEquals(wikiPage.getSignInLableAttribute("data-id"), "userpage");
    }

    @Test(groups = {"addVideo"})
    public void testVisibilityOfContributeDropdown() {
        wikiPage.clickContributeButton();
        assertTrue(wikiPage.getContributeDropdownVisibility());
    }

    @Test(groups = {"addVideo"})
    @Parameters({"addVideoUrl", "videoUrl", "videoTitle"})
    public void testAddVideoLink(String addVideoUrl, String videoUrl, String videoTitle) {
        WikiaAddVideoPage addVideoPage = wikiPage.clickAddVideoLink();
        assertEquals(addVideoPage.getCurrentUrl(), addVideoUrl);

        WikiaFlashElementPage flashElementPage = addVideoPage.inputVideoUrl(videoUrl).submitVideo();
        assertEquals(flashElementPage.getMessage(), "Video page File:".concat(videoTitle).concat(" was successfully added."));

        WikiaFilePage filePage = flashElementPage.clickOnFlashLinkByTitle(videoTitle);
        assertEquals(filePage.getCurrentUrl(), "http://qm-homework.wikia.com/wiki/File:".concat(WebElementHelper.convertSpacesToUnderscores(videoTitle)));
    }


}