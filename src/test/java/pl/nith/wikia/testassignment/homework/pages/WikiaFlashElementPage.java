package pl.nith.wikia.testassignment.homework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.nith.wikia.testassignment.homework.helpers.WebElementHelper;

/**
 * Created by NiTh4r0 on 2015-08-19.
 */
public class WikiaFlashElementPage extends WikiaBasePage {
    private WebElement flashLink;
    @FindBy(how = How.CLASS_NAME, using = "msg")
    private WebElement message;

    public WikiaFlashElementPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WikiaFilePage clickOnFlashLinkByTitle(String title) throws NullPointerException {
        flashLink = WebElementHelper.findLinkElementByHref(getDriver(),
                WebElementHelper.convertSpacesToUnderscores(title));

        if (flashLink != null)
            flashLink.click();
        else throw new NullPointerException();

        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.id("WikiaMainContentContainer")));

        return new WikiaFilePage(getDriver());
    }

    public String getMessage() {
        return message.getText();
    }
}
