package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import strategy.EnvStrategy;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    EnvStrategy envStrategy;
    public static WebDriver driver;

    public static WebDriver getInstance() {

        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void closeBrowser() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        for (int i = 0; i < tabs.size(); i++) {
            driver.switchTo().window(tabs.get(i)).close();
        }
        driver = null;
    }

    public static void quitBrowser() {
        driver.quit();
        driver = null;
    }
}
