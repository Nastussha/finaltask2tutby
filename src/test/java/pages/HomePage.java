package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class HomePage {
    private static final By LOGIN_LINK = By.className("enter");
    private static final By LOGIN = By.name("login");
    private static final By PASSWORD = By.name("password");
    private static final By LOGIN_BUTTON = By.xpath(".//input[contains(@class, 'button auth__enter')]");
    private static final By LOGGED_IN_LINK = By.xpath(".//a[contains(@class, 'logedin')]");
    private static final By EMAIL_BUTTON = By.xpath("//li[@class='auth-navigation__li']/a[contains(text(), 'Почта')]");

    private static final String URL = "https://www.tut.by/";
    private final WebDriver driver;

    public HomePage() {
        this.driver = WebDriverSingleton.getInstance();
    }

    public void load() {
        this.driver.get(URL);
    }

    public LoggedInHomePage login(String username, String password) {
        driver.findElement(LOGIN_LINK).click();
        driver.findElement(LOGIN).sendKeys(username);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new LoggedInHomePage();
    }

    public MailPage openMail() {
        driver.findElement(LOGGED_IN_LINK).click();
        driver.findElement(EMAIL_BUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new MailPage();
    }
}
