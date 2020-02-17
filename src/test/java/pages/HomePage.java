package pages;

import driver.WebDriverSingleton;
import extra.PropertyValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private static final By LOGIN_LINK = By.className("enter");
    private static final By LOGIN = By.name("login");
    private static final By PASSWORD = By.name("password");
    private static final By LOGIN_BUTTON = By.xpath(".//input[contains(@class, 'button auth__enter')]");
    private static final By LOGGED_OUT_RESULT = By.xpath(".//a[@class = 'enter']");

    private static final String URL = PropertyValues.get("url");
    WebDriver driver;

    public HomePage() {
        this.driver = WebDriverSingleton.getInstance().getWebDriver();
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

    public HomePage refreshPage() {
        driver.navigate().refresh();
        return new HomePage();
    }

    public boolean getLogOutResult() {
        return driver.findElement(LOGGED_OUT_RESULT).isDisplayed();
    }
}
