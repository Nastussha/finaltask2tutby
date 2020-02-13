package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import strategy.MyWebDriver;

import java.util.List;

public class TrashFolderPage extends SkeletonPage{
    private static final By ALL_EMAIL_SUBJECT_ON_LIST = By.xpath("//span[contains(@class, 'mail-MessageSnippet-Item_subject')]/span[@title]");

    private static final By TRASH_EMAILS_FOLDER = By.xpath("//a[@href='#trash']");

    private static final By ALL_CHECKBOXES_ON_TRASH_FOLDER = By.cssSelector("._nb-checkbox-input");
    private static final By DELETE_ICON = By.cssSelector(".ns-view-toolbar-button-delete");

    WebDriver driver;
    WebDriverWait wait;

    public TrashFolderPage() {
        this.driver = MyWebDriver.getWebDriverInstance();
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(TRASH_EMAILS_FOLDER));
    }

}
