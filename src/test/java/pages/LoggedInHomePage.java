package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class LoggedInHomePage {
    private static final By LOGGED_IN_LINK = By.xpath(".//a[contains(@class, 'logedin')]");
    private static final By LOGGED_OUT_BUTTON = By.xpath(".//a[contains(@class, 'auth__reg')]");
    private static final By LOGGED_OUT_RESULT = By.xpath(".//a[@class = 'enter']");
    private static final By EMAIL_BUTTON = By.xpath("//li[@class='auth-navigation__li']/a[contains(text(), 'Почта')]");

    private final WebDriver driver;

    public LoggedInHomePage() {
        this.driver = WebDriverSingleton.getInstance();
    }

    public HomePage logout() {
        driver.findElement(LOGGED_IN_LINK).click();
        driver.findElement(LOGGED_OUT_BUTTON).click();
        return new HomePage();
    }

    public InboxFolderPage openMail() {
        driver.findElement(LOGGED_IN_LINK).click();
        driver.findElement(EMAIL_BUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new InboxFolderPage();
    }

    public boolean getLogOutResult() {
        return driver.findElement(LOGGED_OUT_RESULT).isDisplayed();
    }
}
