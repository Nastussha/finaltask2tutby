package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SentEmailPage extends SkeletonPage{

    private static By EMAIL_DONE_MESSAGE = By.cssSelector(".mail-Done-Title");

    WebDriverWait wait;

    public SentEmailPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_DONE_MESSAGE));
    }
}
