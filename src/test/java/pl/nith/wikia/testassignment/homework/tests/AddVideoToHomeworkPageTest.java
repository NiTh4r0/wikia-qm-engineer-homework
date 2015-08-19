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

    @Parameters("baseUrl")
    @BeforeClass
    public void initDriver(String baseUrl) throws Exception {
        driver = new ChromeDriver();
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
}
