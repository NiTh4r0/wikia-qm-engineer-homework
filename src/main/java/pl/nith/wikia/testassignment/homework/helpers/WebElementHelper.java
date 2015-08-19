package pl.nith.wikia.testassignment.homework.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by NiTh4r0 on 2015-08-19.
 */
public class WebElementHelper {
    public static WebElement findLinkElementByHref(WebDriver driver, String href) {
        // Get all the links
        List<WebElement> anchors = driver.findElements(By.tagName("a"));

        // Search through the list to find the link containing specified href
        for (WebElement elem : anchors) {
            String attr = elem.getAttribute("href");
            if (attr != null)
                if (attr.contains(href))
                    return elem;
        }

        return null;
    }

    public static String convertSpacesToUnderscores(String str) {
        return str.replaceAll(" ", "_");
    }
}
