package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import strategy.MyWebDriver;

public class SentEmailPage extends SkeletonPage{

    private static By EMAIL_DONE_MESSAGE = By.cssSelector(".mail-Done-Title");

    WebDriver driver;
    WebDriverWait wait;

    public SentEmailPage() {
        this.driver = MyWebDriver.getWebDriverInstance();
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_DONE_MESSAGE));
    }
}
