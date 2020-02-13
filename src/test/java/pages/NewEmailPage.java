package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import strategy.MyWebDriver;

public class NewEmailPage {

    private static final By NEW_MAIL = By.cssSelector(".mail-ComposeButton");
    private static final By TO_INPUT = By.xpath("//div[contains(@name, 'to')]");
    private static final By SUBJECT_INPUT = By.xpath("//input[contains(@class, 'mail-Compose-Field-Input-Controller')]");
    private static final By SEND_BUTTON = By.id("nb-13");

    private static final By EMAIL_SENT_MESSAGE = By.cssSelector(".mail-Done-Title");
    WebDriver driver;
    WebDriverWait wait;

    public NewEmailPage() {
        this.driver = MyWebDriver.getWebDriverInstance();
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(NEW_MAIL));
    }

    public SentEmailPage sendMail(String toWhomMail, String emailSubject) {
        driver.findElement(NEW_MAIL).click();
        driver.findElement(TO_INPUT).sendKeys(toWhomMail);
        driver.findElement(SUBJECT_INPUT).sendKeys(emailSubject);
        driver.findElement(SEND_BUTTON).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_SENT_MESSAGE));
        return new SentEmailPage();
    }
}
