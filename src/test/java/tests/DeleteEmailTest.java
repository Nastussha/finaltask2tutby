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
public class DeleteEmailTest {

    private static final String TEST_LOGIN = "seleniumtests@tut.by";
    private static final String TEST_PASSWORD = "123456789zxcvbn";

    private static final String TEST_LOGIN_2 = "seleniumtests2@tut.by";
    //private static final String TEST_PASSWORD_2 = "123456789zxcvbn";

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
        emailSubjectSent = GenerateString.generateString();
        mailPage.sendMail(TEST_LOGIN_2, emailSubjectSent);
        mailPage.navigateToSendEmailFolder();
    }

    @AfterEach
    public void closeBrowser() {
        WebDriverSingleton.closeBrowser();
    }

    @AfterAll
    public static void quitBrowser() {
        WebDriverSingleton.quitBrowser();
    }

    @Feature("Delete emails")
    @Description("Verifies if user delete email and the email appears in deleted folder")
    @Issue("ID_4")
    @Test
    void deleteMailFolderCheck() {
        mailPage.deleteMailFromSentFolder();
        mailPage.navigateToTrashEmailFolder();
        Assertions.assertTrue(mailPage.isEmailTitleExists(emailSubjectSent), "Email from Sent folder is deleted and located in Trash folder");
    }
}
