package tests;

import driver.WebDriverSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.MailPage;
import property.Helpers;

public class SendMailTest {

    private static final String TEST_LOGIN = "seleniumtests@tut.by";
    private static final String TEST_PASSWORD = "123456789zxcvbn";

    private static final String TEST_LOGIN_2 = "seleniumtests2@tut.by";
    private static final String TEST_PASSWORD_2 = "123456789zxcvbn";

    WebDriver driver;
    HomePage homePage;
    MailPage mailPage;
    String emailSubjectSent;

    @BeforeEach
    public void openBrowser() {
        driver = WebDriverSingleton.getInstance();
        homePage = new HomePage();
        homePage.load();
        homePage.login(TEST_LOGIN, TEST_PASSWORD);
        mailPage = homePage.openMail();
    }

    @AfterEach
    public void closeBrowser() {
        WebDriverSingleton.closeBrowser();
    }

    @AfterAll
    public static void quitBrowser() {
        WebDriverSingleton.quitBrowser();
    }

    @Test
    void sendMailSecondUserCheck() {
        emailSubjectSent = Helpers.generateString();
        mailPage.sendMail(TEST_LOGIN_2, emailSubjectSent);
        mailPage.logout();
        mailPage.switchToPreviousTab();
        mailPage = null;
        homePage.load();
        homePage.login(TEST_LOGIN_2, TEST_PASSWORD_2);
        mailPage = homePage.openMail();
        Assertions.assertTrue(mailPage.isEmailTitleExists(emailSubjectSent), "Email is sent and received by the second user");
    }

    @Test
    void sendMailFolderCheck() {
        emailSubjectSent = Helpers.generateString();
        mailPage.sendMail(TEST_LOGIN_2, emailSubjectSent);
        mailPage.navigateToSendEmailFolder();
        Assertions.assertTrue(mailPage.isEmailTitleExists(emailSubjectSent), "Email is sent and displayed in Sent email folder");
    }
}
