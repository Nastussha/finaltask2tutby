package strategy;

import org.openqa.selenium.WebDriver;

public class WebDriverLaunch {
    MyWebDriver myWebDriver;


    public WebDriver launchDriver(String mvnStrategy, String browser) {
        if (mvnStrategy.equals("local")) {
            myWebDriver = new MyWebDriver(new LocalEnvDriver(), browser);
        } else if (mvnStrategy.equals("saucelabs")) {
            myWebDriver = new MyWebDriver(new SauceLabsEnvDriver(), browser);
        } else if (mvnStrategy.equals("docker")) {
            myWebDriver = new MyWebDriver(new DockerEnvDriver(), browser);
        }
        return myWebDriver.getWebDriverInstance();
    }

    public MyWebDriver get(){
        return myWebDriver;
    }
}
