package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import strategy.MyWebDriver;

import java.util.ArrayList;

public class YandexPage {

    WebDriver driver;
    WebDriverWait wait;


    public YandexPage() {
        this.driver = MyWebDriver.getWebDriverInstance();
        wait = new WebDriverWait(driver, 10);
    }

    public HomePage switchToHomePageTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
        return new HomePage();
    }
}
