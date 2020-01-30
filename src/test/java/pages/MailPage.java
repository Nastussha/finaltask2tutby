package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MailPage {
    private static final By NEW_MAIL = By.cssSelector(".mail-ComposeButton");
    private static final By TO_INPUT = By.xpath("//div[contains(@name, 'to')]");
    private static final By SUBJECT_INPUT = By.xpath("//input[contains(@class, 'mail-Compose-Field-Input-Controller')]");
    private static final By SEND_BUTTON = By.id("nb-13");
    private static final By USER_ICON = By.cssSelector(".mail-User");
    private static final By LOGOUT_LINK = By.xpath("//a[@data-metric='Sign out of Yandex services']");
    private static final By SENT_EMAILS_FOLDER = By.xpath("//a[@href='#sent']");
    private static final By TRASH_EMAILS_FOLDER = By.xpath("//a[@href='#trash']");
    private static final By ALL_CHECKBOXES_ON_SENT_FOLDER = By.cssSelector("._nb-checkbox-input");
    private static final By DELETE_ICON = By.cssSelector(".ns-view-toolbar-button-delete");

    private static final By LOGGED_IN_RESULT = By.id("recipient-1");
    private static final By ALL_EMAIL_SUBJECT_ON_LIST = By.xpath("//span[contains(@class, 'mail-MessageSnippet-Item_subject')]/span[@title]");
    private static final By EMAIL_SENT_MESSAGE = By.cssSelector(".mail-Done-Title");
    private final WebDriver driver;
    WebDriverWait wait;

    public MailPage() {
        this.driver = WebDriverSingleton.getInstance();
    }

    public MailPage sendMail(String toWhomMail, String emailSubject) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(NEW_MAIL));
        driver.findElement(NEW_MAIL).click();
        driver.findElement(TO_INPUT).sendKeys(toWhomMail);
        driver.findElement(SUBJECT_INPUT).sendKeys(emailSubject);
        driver.findElement(SEND_BUTTON).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL_SENT_MESSAGE));
        return new MailPage();
    }

    public MailPage logout() {
        driver.findElement(USER_ICON).click();
        driver.findElement(LOGOUT_LINK).click();
        return new MailPage();
    }

    public HomePage switchToPreviousTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        return new HomePage();
    }

    public MailPage navigateToSendEmailFolder() {
        driver.findElement(SENT_EMAILS_FOLDER).click();
        return new MailPage();
    }

    public MailPage navigateToTrashEmailFolder() {
        driver.findElement(TRASH_EMAILS_FOLDER).click();
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALL_EMAIL_SUBJECT_ON_LIST));
        return new MailPage();
    }

    public MailPage deleteMailFromSentFolder() {
        wait.until(ExpectedConditions.presenceOfElementLocated(ALL_CHECKBOXES_ON_SENT_FOLDER));
        List<WebElement> checkboxes = driver.findElements(ALL_CHECKBOXES_ON_SENT_FOLDER);
        Actions act = new Actions(driver);
        act.moveToElement(checkboxes.get(0)).click().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(DELETE_ICON));
        driver.findElement(DELETE_ICON).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALL_CHECKBOXES_ON_SENT_FOLDER));
        return new MailPage();
    }

    public boolean isEmailTitleExists(String emailSubjectSent) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALL_EMAIL_SUBJECT_ON_LIST));
            List<WebElement> emailSubject = driver.findElements(ALL_EMAIL_SUBJECT_ON_LIST);
            String emailText = emailSubject.get(0).getText();
            return emailText.equals(emailSubjectSent);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean getLoggedInResult() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(LOGGED_IN_RESULT));
            return driver.findElement(LOGGED_IN_RESULT).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
