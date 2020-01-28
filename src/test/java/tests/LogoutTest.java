package tests;

import driver.WebDriverSingleton;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoggedInHomePage;

public class LogoutTest {
    private static final String TEST_LOGIN = "seleniumtests@tut.by";
    private static final String TEST_PASSWORD = "123456789zxcvbn";

    WebDriver driver;
    HomePage homePage;
    LoggedInHomePage loggedInHomePage;

    @BeforeEach
    public void openBrowser() {
        driver = WebDriverSingleton.getInstance();
        homePage = new HomePage();
        homePage.load();
        loggedInHomePage = homePage.login(TEST_LOGIN, TEST_PASSWORD);
    }

    @AfterEach
    public void closeBrowser() {
        WebDriverSingleton.closeBrowser();
    }


    @Feature("Authorization")
    @Description("Verifies if user can log out")
    @Issue("ID_2")
    @Test
    public void logout() {
        loggedInHomePage.logout();
        Assertions.assertTrue(loggedInHomePage.getLogOutResult(), "User is not logged out");
    }
}
