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
import pages.InboxFolderPage;
import strategy.WebDriverLaunch;

@ExtendWith(MyExtension.class)
public class AuthorizationTest {

    WebDriver driver;

    HomePage homePage;
    InboxFolderPage inboxFolderPage;
    LoggedInHomePage loggedInHomePage;
    WebDriverLaunch webDriverLaunch;

    @BeforeEach
    public void openBrowser() {
        webDriverLaunch = new WebDriverLaunch();
        driver = webDriverLaunch.launchDriver(System.getProperty("strategy"));
    }

    @AfterEach
    public void closeBrowser() {
        webDriverLaunch.get().closeDriver();
    }

    @Feature("Authorization")
    @Description("Verifies if user can log in")
    @Issue("ID_1")
    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1)
    public void login(String username, String password) {
        homePage = new HomePage();
        homePage.load();
        loggedInHomePage = homePage.login(username, password);
        inboxFolderPage = loggedInHomePage.openMail();
        Assertions.assertTrue(inboxFolderPage.getLoggedInResult(), "User is logged in");
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
