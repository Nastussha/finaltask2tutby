package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrashFolderPage extends SkeletonPage{

    private static final By ALL_EMAIL_SUBJECT_ON_LIST = By.xpath("//span[contains(@class, 'mail-MessageSnippet-Item_subject')]/span[@title]");
    private static final By NO_EMAILS_MESSAGE = By.cssSelector(".b-messages__placeholder-item");

    private static final By TRASH_EMAILS_FOLDER = By.xpath("//a[@href='#trash']");

    private static final By ALL_CHECKBOXES_ON_TRASH_FOLDER = By.cssSelector("._nb-checkbox-input");
    private static final By DELETE_ICON = By.cssSelector(".ns-view-toolbar-button-delete");

    WebDriverWait wait;

    public TrashFolderPage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfAllElementsLocatedBy(ALL_EMAIL_SUBJECT_ON_LIST),
                ExpectedConditions.presenceOfAllElementsLocatedBy(NO_EMAILS_MESSAGE)
        ));
    }

}
