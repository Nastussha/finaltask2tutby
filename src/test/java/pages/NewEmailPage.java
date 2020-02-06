package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewEmailPage {

    private static final By NEW_MAIL = By.cssSelector(".mail-ComposeButton");
    private static final By TO_INPUT = By.xpath("//div[contains(@name, 'to')]");
    private static final By SUBJECT_INPUT = By.xpath("//input[contains(@class, 'mail-Compose-Field-Input-Controller')]");
    private static final By SEND_BUTTON = By.id("nb-13");

    private static final By EMAIL_SENT_MESSAGE = By.cssSelector(".mail-Done-Title");
    WebDriver driver;
    WebDriverWait wait;

    public NewEmailPage() {
        this.driver = WebDriverSingleton.getInstance();
    }
    //WebDriverWait wait;

    public InboxFolderPage sendMail(String toWhomMail, String emailSubject) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(NEW_MAIL));
        driver.findElement(NEW_MAIL).click();
        driver.findElement(TO_INPUT).sendKeys(toWhomMail);
        driver.findElement(SUBJECT_INPUT).sendKeys(emailSubject);
        driver.findElement(SEND_BUTTON).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_SENT_MESSAGE));
        return new InboxFolderPage();
    }
}
