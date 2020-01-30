package tests;

import driver.WebDriverSingleton;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import junit.MyExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoggedInHomePage;

@ExtendWith(MyExtension.class)
public class LogoutTest {
    //private static final String TEST_LOGIN = "seleniumtests@tut.by";
    //private static final String TEST_PASSWORD = "123456789zxcvbn";

    WebDriver driver;
    HomePage homePage;
    LoggedInHomePage loggedInHomePage;

    @BeforeEach
    public void openBrowser() {
        driver = WebDriverSingleton.getInstance();
    }

    @AfterEach
    public void closeBrowser() {
        WebDriverSingleton.closeBrowser();
    }

    @AfterAll
    public static void quitBrowser() {
        WebDriverSingleton.quitBrowser();
    }


    @Feature("Authorization")
    @Description("Verifies if user can log out")
    @Issue("ID_5")
    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1)
    public void logout(String username, String password) {
        homePage = new HomePage();
        homePage.load();
        loggedInHomePage = homePage.login(username, password);
        loggedInHomePage.logout();
        Assertions.assertTrue(loggedInHomePage.getLogOutResult(), "User is not logged out");
    }
}
