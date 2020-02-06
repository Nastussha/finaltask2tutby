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

public class InboxFolderPage {
    private static final By NEW_MAIL = By.cssSelector(".mail-ComposeButton");

    private static final By USER_ICON = By.cssSelector(".mail-User");
    private static final By LOGOUT_LINK = By.xpath("//a[@data-metric='Sign out of Yandex services']");

    private static final By SENT_EMAILS_FOLDER = By.xpath("//a[@href='#sent']");
    private static final By TRASH_EMAILS_FOLDER = By.xpath("//a[@href='#trash']");

    private static final By LOGGED_IN_RESULT = By.id("recipient-1");
    private static final By ALL_EMAIL_SUBJECT_ON_LIST = By.xpath("//span[contains(@class, 'mail-MessageSnippet-Item_subject')]/span[@title]");
    //private static final By EMAIL_SENT_MESSAGE = By.cssSelector(".mail-Done-Title");

    private final WebDriver driver;
    WebDriverWait wait;

    public InboxFolderPage() {
        this.driver = WebDriverSingleton.getInstance();
    }

    public NewEmailPage navigateToNewEmailPage(){
        driver.findElement(NEW_MAIL);
        return new NewEmailPage();
    }

    public SentFolderPage navigateToSendEmailFolder() {
        driver.findElement(SENT_EMAILS_FOLDER).click();
        return new SentFolderPage();
    }

    public InboxFolderPage navigateToTrashEmailFolder() {
        driver.findElement(TRASH_EMAILS_FOLDER).click();
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALL_EMAIL_SUBJECT_ON_LIST));
        return new InboxFolderPage();
    }

    public InboxFolderPage logout() {
        driver.findElement(USER_ICON).click();
        driver.findElement(LOGOUT_LINK).click();
        return new InboxFolderPage();
    }

    public HomePage switchToPreviousTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        return new HomePage();
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
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(LOGGED_IN_RESULT));
            return driver.findElement(LOGGED_IN_RESULT).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
