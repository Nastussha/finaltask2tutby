package strategy;

import org.openqa.selenium.WebDriver;

public class WebDriverLaunch {
    MyWebDriver myWebDriver;


    public WebDriver launchDriver(String mvnStrategy) {
        if (mvnStrategy.equals("local")) {
            myWebDriver = new MyWebDriver(new LocalEnvDriver());
        } else if (mvnStrategy.equals("saucelabs")) {
            myWebDriver = new MyWebDriver(new SauceLabsEnvDriver());
        } else if (mvnStrategy.equals("docker")) {
            myWebDriver = new MyWebDriver(new DockerEnvDriver());
        }
        return myWebDriver.getWebDriverInstance();
    }

    public MyWebDriver get(){
        return myWebDriver;
    }
}
