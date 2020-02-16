package strategy;

import org.openqa.selenium.WebDriver;

public class MyWebDriver {

    private static EnvStrategy envType;

    public MyWebDriver(EnvStrategy envType) {
        this.envType = envType;
    }

    public static WebDriver getWebDriverInstance() {
        return envType.createStrategyDriver();
    }

    public void closeDriver(){
        envType.closeStrategyDriver();
    }

    public void quitDriver(){
        envType.quitStrategyDriver();
    }

}
