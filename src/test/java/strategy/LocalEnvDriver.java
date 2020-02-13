package strategy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;

public class LocalEnvDriver implements EnvStrategy {

    private static WebDriver driver;

    private static WebDriver getInstance() {
        if (System.getProperty("browser").equals("chrome")){
            return initChromeDriver();
        } else {
            return initFirefoxDriver();
        }
    }

    private static WebDriver initFirefoxDriver(){
        if (driver == null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    private static WebDriver initChromeDriver(){
        if (driver == null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    private static void closeBrowser(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        for (int i = 0; i < tabs.size(); i++){
            driver.switchTo().window(tabs.get(i)).close();
        }
        driver = null;
    }

    @Override
    public WebDriver createStrategyDriver() {
        return LocalEnvDriver.getInstance();
    }

    @Override
    public void closeStrategyDriver() {
        closeBrowser();
    }

}
