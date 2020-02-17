package driver;

import extra.PropertyValues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {


  private static ThreadLocal<WebDriverSingleton> instance = new ThreadLocal<>();
  private WebDriver driver;

  private WebDriverSingleton() {
    String browser = System.getProperty("browser") != null ? System.getProperty("browser") : "chrome";
    boolean useGrid = System.getProperty("grid") != null;

    if (useGrid) {
      initializeRemoteWebDriver(browser);
    } else {
      initializeLocalWebDriver(browser);
    }

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
  }

  public static WebDriverSingleton getInstance() {
    if (instance.get() == null) {
      synchronized (WebDriverSingleton.class) {
        if (instance.get() == null) {
          instance.set(new WebDriverSingleton());
        }
      }
    }

    return instance.get();
  }

  private void initializeRemoteWebDriver(String browser) {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName(browser);

    String host = System.getProperty("host");
    String port = System.getProperty("port");
    String value = host != null ^ port != null || host == null
        ? PropertyValues.get("grid.url")
        : String.format("http://%s:%s/wd/hub", host, port);

    try {
      URL gridUrl = new URL(value);
      driver = new RemoteWebDriver(gridUrl, capabilities);
      ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
    } catch (MalformedURLException e) {
      e.printStackTrace();
      throw new RuntimeException("Can't connect to remote webdriver");
    }
  }

  private void initializeLocalWebDriver(String browser) {
    switch (browser) {
      case BrowserType.FIREFOX:
        driver = new FirefoxDriver();
        break;
      case BrowserType.IE:
        driver = new InternetExplorerDriver();
        break;
      default:
        driver = new ChromeDriver();
        break;
    }
  }

  public WebDriver getWebDriver() {
    return driver;
  }

  public void closeWebDriver() {
    if (instance.get() != null) {
      driver.quit();
      instance.set(null);
    }
  }
}
