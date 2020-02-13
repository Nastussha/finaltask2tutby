package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import strategy.MyWebDriver;

public class SentEmailPage extends SkeletonPage{

    WebDriver driver;
    WebDriverWait wait;

    public SentEmailPage() {
        this.driver = MyWebDriver.getWebDriverInstance();
        wait = new WebDriverWait(driver, 10);
    }

}
