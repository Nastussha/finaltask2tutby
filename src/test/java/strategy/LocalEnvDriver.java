package strategy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class LocalEnvDriver implements Strategy {

    private static WebDriver driver;

    private static WebDriver getInstance() {
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        return driver;
    }

    private static void closeBrowser(){
        driver.close();
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
