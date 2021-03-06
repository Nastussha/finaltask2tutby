package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public abstract class SkeletonPage {

    private static final By NEW_MAIL = By.cssSelector(".mail-ComposeButton");

    private static final By USER_ICON = By.cssSelector(".mail-User");
    private static final By LOGOUT_LINK = By.xpath("//a[@data-metric='Sign out of Yandex services']");

    private static final By INBOX_EMAILS_FOLDER = By.xpath("//a[@href='#inbox']");
    private static final By SENT_EMAILS_FOLDER = By.xpath("//a[@href='#sent']");
    private static final By TRASH_EMAILS_FOLDER = By.xpath("//a[@href='#trash']");

    private static final By ALL_EMAIL_SUBJECT_ON_LIST = By.xpath("//span[contains(@class, 'mail-MessageSnippet-Item_subject')]/span[@title]");

    private static final By ALL_LETTER_CHECKBOXES_IN_FOLDER = By.cssSelector("._nb-checkbox-input");
    private static final By NO_EMAILS_MESSAGE = By.xpath("//div[@class='b-messages__placeholder-item'][1]");
    private static final By DELETE_ICON = By.cssSelector(".ns-view-toolbar-button-delete");

    protected WebDriver driver;
    WebDriverWait wait;

    public SkeletonPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(INBOX_EMAILS_FOLDER));
    }


    public NewEmailPage navigateToNewEmailPage() {
        driver.findElement(NEW_MAIL).click();
        return new NewEmailPage();
    }

    public InboxFolderPage navigateToInboxEmailFolder() {
        driver.findElement(INBOX_EMAILS_FOLDER).click();
        return new InboxFolderPage();
    }

    public SentFolderPage navigateToSendEmailFolder() {
        driver.findElement(SENT_EMAILS_FOLDER).click();
        return new SentFolderPage();
    }

    public TrashFolderPage navigateToTrashEmailFolder() {
        driver.findElement(TRASH_EMAILS_FOLDER).click();
        return new TrashFolderPage();
    }

    public YandexPage logout() {
        driver.findElement(USER_ICON).click();
        driver.findElement(LOGOUT_LINK).click();
        return new YandexPage();
    }

    public void deleteLastMailInFolder() {
        try {
            List<WebElement> checkboxes = driver.findElements(ALL_LETTER_CHECKBOXES_IN_FOLDER);
            Actions act = new Actions(driver);
            act.moveToElement(checkboxes.get(0)).click().perform();
            driver.findElement(DELETE_ICON).click();
        } catch (IndexOutOfBoundsException e) {
        } catch (NullPointerException e) {
        }
    }

    public boolean isEmptyEmailsFolder() {
        try {
            driver.findElement(NO_EMAILS_MESSAGE);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public void deleteLastEmailByName(String emailName) {
        try {
            List<WebElement> allSubjects = driver.findElements(ALL_EMAIL_SUBJECT_ON_LIST);
            List<WebElement> allCheckboxes = driver.findElements(ALL_LETTER_CHECKBOXES_IN_FOLDER);
            if (allSubjects.get(0).getText().equals(emailName)) {
                Actions act = new Actions(driver);
                act.moveToElement(allCheckboxes.get(0)).click().perform();
                driver.findElement(DELETE_ICON).click();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean isEmailTitleExists(String emailSubjectSent) {
        try {
            List<WebElement> emailSubject = driver.findElements(ALL_EMAIL_SUBJECT_ON_LIST);
            String emailText = emailSubject.get(0).getText();
            return emailText.equals(emailSubjectSent);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
}
