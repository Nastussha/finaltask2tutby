package strategy;

import org.openqa.selenium.WebDriver;

public class MyWebDriver {

    Strategy strategy;

    public MyWebDriver(Strategy strategy) {
        this.strategy = strategy;
    }

    public WebDriver initializeWebDriver() {
        return strategy.createStrategyDriver();
    }

    public void closeDriver(){
        strategy.closeStrategyDriver();
    }

}
