package strategy;

import org.openqa.selenium.WebDriver;

public interface Strategy {

    WebDriver createStrategyDriver();
    void closeStrategyDriver();

}
