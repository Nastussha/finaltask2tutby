package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class InboxFolderPage extends SkeletonPage{
    private static final By NEW_MAIL = By.cssSelector(".mail-ComposeButton");

    private static final By USER_ICON = By.cssSelector(".mail-User");
    private static final By LOGOUT_LINK = By.xpath("//a[@data-metric='Sign out of Yandex services']");

    private static final By INBOX_EMAILS_FOLDER = By.xpath("//a[@href='#inbox']");
    private static final By SENT_EMAILS_FOLDER = By.xpath("//a[@href='#sent']");
    private static final By TRASH_EMAILS_FOLDER = By.xpath("//a[@href='#trash']");

    private static final By LOGGED_IN_RESULT = By.id("recipient-1");
    private static final By ALL_EMAIL_SUBJECT_ON_LIST = By.xpath("//span[contains(@class, 'mail-MessageSnippet-Item_subject')]/span[@title]");

    private static final By ALL_CHECKBOXES_ON_INBOX_FOLDER = By.cssSelector("._nb-checkbox-input");
    private static final By DELETE_ICON = By.cssSelector(".ns-view-toolbar-button-delete");
    //private static final By EMAIL_SENT_MESSAGE = By.cssSelector(".mail-Done-Title");

    WebDriverWait wait;


    public InboxFolderPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(INBOX_EMAILS_FOLDER));
    }

    public HomePage switchToPreviousTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        return new HomePage();
    }

    public boolean getLoggedInResult() {
        try {
            return driver.findElement(LOGGED_IN_RESULT).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
