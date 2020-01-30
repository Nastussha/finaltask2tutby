package tests;

import driver.WebDriverSingleton;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import junit.MyExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.MailPage;
import extra.GenerateString;

@ExtendWith(MyExtension.class)
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

    @Feature("Send email")
    @Description("Verifies if user can send email and the second user receives email")
    @Issue("ID_2")
    @Test
    void sendMailSecondUserCheck() {
        emailSubjectSent = GenerateString.generateString();
        mailPage.sendMail(TEST_LOGIN_2, emailSubjectSent);
        mailPage.logout();
        mailPage.switchToPreviousTab();
        mailPage = null;
        homePage.load();
        homePage.login(TEST_LOGIN_2, TEST_PASSWORD_2);
        mailPage = homePage.openMail();
        Assertions.assertTrue(mailPage.isEmailTitleExists(emailSubjectSent), "Email is sent and received by the second user");
    }

    @Feature("Send email")
    @Description("Verifies if user can send email and email appears in sent folder")
    @Issue("ID_3")
    @Test
    void sendMailFolderCheck() {
        emailSubjectSent = GenerateString.generateString();
        mailPage.sendMail(TEST_LOGIN_2, emailSubjectSent);
        mailPage.navigateToSendEmailFolder();
        Assertions.assertTrue(mailPage.isEmailTitleExists(emailSubjectSent), "Email is sent and displayed in Sent email folder");
    }
}
