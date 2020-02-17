package tests;

import driver.WebDriverSingleton;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import junit.MyExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.HomePage;
import pages.InboxFolderPage;
import pages.LoggedInHomePage;

import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MyExtension.class)
public class AuthorizationTest {

    HomePage homePage;
    InboxFolderPage inboxFolderPage;
    LoggedInHomePage loggedInHomePage;

    @BeforeAll
    public void openBrowser() {
        homePage = new HomePage();
        homePage.load();
    }

    @AfterEach
    public void tearDown() {
        Set<String> windowHandles = WebDriverSingleton.getInstance().getWebDriver().getWindowHandles();
        if (windowHandles.size() > 1) {
            WebDriverSingleton.getInstance().getWebDriver().close();
            WebDriverSingleton.getInstance().getWebDriver().switchTo().window(windowHandles.iterator().next());
        }
        WebDriverSingleton.getInstance().getWebDriver().manage().deleteAllCookies();
        WebDriverSingleton.getInstance().getWebDriver().navigate().refresh();
    }

    @AfterAll
    public static void quitBrowser(){
        WebDriverSingleton.getInstance().closeWebDriver();
    }

    @Feature("Authorization")
    @Description("Verifies if user can log in")
    @Issue("ID_1")
    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1)
    public void login(String username, String password) {
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
        loggedInHomePage = homePage.login(username, password);
        homePage = loggedInHomePage.logout();
        Assertions.assertTrue(homePage.getLogOutResult(), "User is not logged out");
    }
}
