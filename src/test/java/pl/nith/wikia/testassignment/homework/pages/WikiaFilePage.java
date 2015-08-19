package pl.nith.wikia.testassignment.homework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by NiTh4r0 on 2015-08-19.
 */
public class WikiaFilePage extends WikiaBasePage {
    public WikiaFilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
