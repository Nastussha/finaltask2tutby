package strategy;

import org.openqa.selenium.WebDriver;

public interface EnvStrategy {

    WebDriver createStrategyDriver();
    void closeStrategyDriver();

}
