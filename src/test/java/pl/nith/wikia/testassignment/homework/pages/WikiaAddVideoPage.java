package pl.nith.wikia.testassignment.homework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by NiTh4r0 on 2015-08-19.
 */
public class WikiaAddVideoPage extends WikiaBasePage {
    private WebElement wpWikiaVideoAddUrl;
    @FindBy(how = How.XPATH, using = "//input[@value='Add']")
    private WebElement submitButton;

    public WikiaAddVideoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WikiaAddVideoPage inputVideoUrl(String url) {
        wpWikiaVideoAddUrl.sendKeys(url);

        return this;
    }

    public WikiaFlashElementPage submitVideo() {
        submitButton.click();

        return new WikiaFlashElementPage(getDriver());
    }
}
