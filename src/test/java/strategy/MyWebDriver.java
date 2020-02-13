package strategy;

import org.openqa.selenium.WebDriver;

public class MyWebDriver {

    private static EnvStrategy envType;
    private static String browserType;

    public MyWebDriver(EnvStrategy envType, String browserType) {
        this.envType = envType;
        this.browserType = browserType;
    }

    public String getBrowserType() {
        return browserType;
    }

    public static WebDriver getWebDriverInstance() {
        return envType.createStrategyDriver();
    }

    public void closeDriver(){
        envType.closeStrategyDriver();
    }

}
