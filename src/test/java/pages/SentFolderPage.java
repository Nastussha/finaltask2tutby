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

public class SentFolderPage {

    private static final By ALL_EMAIL_SUBJECT_ON_LIST = By.xpath("//span[contains(@class, 'mail-MessageSnippet-Item_subject')]/span[@title]");

    private static final By TRASH_EMAILS_FOLDER = By.xpath("//a[@href='#trash']");

    private static final By ALL_CHECKBOXES_ON_SENT_FOLDER = By.cssSelector("._nb-checkbox-input");
    private static final By DELETE_ICON = By.cssSelector(".ns-view-toolbar-button-delete");

    WebDriver driver;

    public SentFolderPage() {
        this.driver = WebDriverSingleton.getInstance();
    }

    public InboxFolderPage deleteMailFromSentFolder() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ALL_CHECKBOXES_ON_SENT_FOLDER));
        List<WebElement> checkboxes = driver.findElements(ALL_CHECKBOXES_ON_SENT_FOLDER);
        Actions act = new Actions(driver);
        act.moveToElement(checkboxes.get(0)).click().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(DELETE_ICON));
        driver.findElement(DELETE_ICON).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALL_CHECKBOXES_ON_SENT_FOLDER));
        return new InboxFolderPage();
    }

    public TrashFolderPage navigateToTrashEmailFolder() {
        driver.findElement(TRASH_EMAILS_FOLDER).click();
        /*
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALL_EMAIL_SUBJECT_ON_LIST));
         */
        return new TrashFolderPage();
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
}
