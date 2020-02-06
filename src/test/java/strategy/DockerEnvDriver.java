package strategy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DockerEnvDriver implements Strategy{
    public static WebDriver driver;
    public static final String URL = "http://localhost:4444/wd/hub/";


    public static WebDriver getInstance() {
        try {
            if (driver == null) {
                DesiredCapabilities caps = DesiredCapabilities.firefox();
                caps.setCapability("platform", "Linux");
                caps.setCapability("version", "71.0");
                driver = new RemoteWebDriver(new URL(URL), caps);
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public static void closeBrowser() {
        driver.close();
        driver = null;
    }

    @Override
    public WebDriver createStrategyDriver() {
        return DockerEnvDriver.getInstance();
    }

    @Override
    public void closeStrategyDriver() {

    }
}
