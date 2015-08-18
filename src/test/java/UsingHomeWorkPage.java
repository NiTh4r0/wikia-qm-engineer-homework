import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by NiTh4r0 on 2015-08-18.
 */
public class UsingHomeWorkPage {
    public static void  main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia");

        WikiaHomeworkPage page = PageFactory.initElements(driver, WikiaHomeworkPage.class);

        page.moveMouseToLoginDropdown(driver);

        // page.searchInputTest();
    }
}
