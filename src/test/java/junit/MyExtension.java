package junit;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.opentest4j.AssertionFailedError;
import strategy.MyWebDriver;

public class MyExtension implements TestWatcher, TestExecutionExceptionHandler {



    @Attachment(value = "Browser Information", type = "txt")
    private String showBrowserInfo() {
        RemoteWebDriver driver = (RemoteWebDriver) MyWebDriver.getWebDriverInstance();
        Capabilities capabilities = driver.getCapabilities();
        String browserName = capabilities.getBrowserName();
        String browserVersion = capabilities.getVersion();
        String messageBrowser = browserName + " " + browserVersion;
        return messageBrowser;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] captureScreenshot() {
        return ((TakesScreenshot) MyWebDriver.getWebDriverInstance()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        showBrowserInfo();
    }

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof AssertionFailedError) {
            captureScreenshot();
        }
        throw throwable;
    }
}
