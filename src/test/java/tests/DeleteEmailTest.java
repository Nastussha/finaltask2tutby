package tests;

import driver.WebDriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.MailPage;
import property.Helpers;

public class DeleteEmailTest {

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
        emailSubjectSent = Helpers.generateString();
        mailPage.sendMail(TEST_LOGIN_2, emailSubjectSent);
        mailPage.navigateToSendEmailFolder();
    }

    @AfterEach
    public void closeBrowser() {
        WebDriverSingleton.closeBrowser();
    }

    @Test
    void deleteMailFolderCheck() {
        mailPage.deleteMailFromSentFolder();
        mailPage.navigateToTrashEmailFolder();
        Assertions.assertTrue(mailPage.isEmailTitleExists(emailSubjectSent), "Email from Sent folder is deleted and located in Trash folder");
    }
}
