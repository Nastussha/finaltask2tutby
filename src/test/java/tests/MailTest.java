package tests;

import extra.PropertyValues;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import junit.MyExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.*;
import extra.GenerateString;
import strategy.WebDriverLaunch;

@ExtendWith(MyExtension.class)
public class MailTest {

    WebDriver driver;
    WebDriverLaunch webDriverLaunch;
    HomePage homePage;
    LoggedInHomePage loggedInHomePage;
    InboxFolderPage inboxFolderPage;
    NewEmailPage newEmailPage;
    SentFolderPage sentFolderPage;
    TrashFolderPage trashFolderPage;
    String emailSubjectSent;

    @BeforeEach
    public void openBrowser() {
        webDriverLaunch = new WebDriverLaunch();
        //driver = webDriverLaunch.launchDriver(System.getProperty("strategy"));
        driver = webDriverLaunch.launchDriver("local", "firefox");
        homePage = new HomePage();
        homePage.load();
        loggedInHomePage = homePage.login(PropertyValues.get("test_login"), PropertyValues.get("test_password"));
        inboxFolderPage = loggedInHomePage.openMail();
    }

    @AfterEach
    public void closeBrowser() {
        if (inboxFolderPage != null){
            inboxFolderPage.deleteLastMailInFolder();
            inboxFolderPage = null;
        }
        if (sentFolderPage != null){
            sentFolderPage.deleteLastMailInFolder();
            sentFolderPage = null;
        }
        if (trashFolderPage != null){
            trashFolderPage.deleteLastMailInFolder();
            trashFolderPage = null;
        }
        webDriverLaunch.get().closeDriver();
    }

    @Feature("Send email")
    @Description("Verifies if user can send email and the second user receives email")
    @Issue("ID_2")
    @Test
    void sendMailSecondUserCheck() {
        newEmailPage = inboxFolderPage.navigateToNewEmailPage();
        emailSubjectSent = GenerateString.generateString();
        homePage = newEmailPage.sendMail(PropertyValues.get("test_login2"), emailSubjectSent)
                .logout().switchToHomePageTab();
        homePage.refreshPage();
        loggedInHomePage = homePage.login(PropertyValues.get("test_login2"), PropertyValues.get("test_password2"));
        inboxFolderPage = loggedInHomePage.openMail();
        Assertions.assertTrue(inboxFolderPage.isEmailTitleExists(emailSubjectSent), "Email is sent and received by the second user");
    }

    @Feature("Send email")
    @Description("Verifies if user can send email and email appears in sent folder")
    @Issue("ID_3")
    @Test
    void sendMailFolderCheck() {
        newEmailPage = inboxFolderPage.navigateToNewEmailPage();
        emailSubjectSent = GenerateString.generateString();
        sentFolderPage = newEmailPage.sendMail(PropertyValues.get("test_login2"), emailSubjectSent)
                .navigateToSendEmailFolder();
        Assertions.assertTrue(sentFolderPage.isEmailTitleExists(emailSubjectSent), "Email is sent and displayed in Sent email folder");
    }

    @Feature("Delete emails")
    @Description("Verifies if user delete email and the email appears in deleted folder")
    @Issue("ID_4")
    @Test
    void deleteMailFolderCheck() {
        newEmailPage = inboxFolderPage.navigateToNewEmailPage();
        emailSubjectSent = GenerateString.generateString();
        sentFolderPage = newEmailPage.sendMail(PropertyValues.get("test_login2"), emailSubjectSent)
                .navigateToSendEmailFolder();
        sentFolderPage.deleteLastMailInFolder();
        trashFolderPage = sentFolderPage.navigateToTrashEmailFolder();
        Assertions.assertTrue(trashFolderPage.isEmailTitleExists(emailSubjectSent), "Email from Sent folder is deleted and located in Trash folder");
    }
}
