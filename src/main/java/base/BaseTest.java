package base;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import util.Constants;
import util.reporter.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITest;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseTest extends AbstractTestNGCucumberTests implements ITest {

    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    protected String currentAnnotatedMethodName = "";

    public static WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

    @AfterMethod
    public void setNullTestName() {
        currentAnnotatedMethodName = null;
    }

    @Override
    public String getTestName() {
        return currentAnnotatedMethodName;
    }

    @BeforeMethod
    public void setTestName(Method method) {
        currentAnnotatedMethodName = "";
        try {
            String testName = method.getAnnotation(Test.class).testName();
            if (!testName.isEmpty())
                currentAnnotatedMethodName = testName;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void startChrome() {
        String platform = System.getProperty("os.name");

        String driversFolder = Constants.DEFAULT_LIB_DIR + File.separator;
        String pathToDriver = null;
        if (System.getProperty("path.to.driver") != null && !System.getProperty("path.to.driver").isEmpty()) {
            pathToDriver = (platform.contains("Wind")) ? System.getProperty("path.to.driver") : driversFolder + "linux" + File.separator + "chromedriver";
        } else {
            pathToDriver = (platform.contains("Wind")) ? driversFolder + "chromedriver.exe" : driversFolder + "linux" + File.separator + "chromedriver";
        }

        System.setProperty("webdriver.chrome.driver", pathToDriver);

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", System.getProperty("download.dir"));

        ChromeOptions capabilities = new ChromeOptions();

        capabilities.setExperimentalOption("prefs", chromePrefs);

        if (Boolean.parseBoolean(System.getProperty("headless"))) {
            capabilities.addArguments("--headless");
            capabilities.addArguments("--window-size=1920,1080");
        }
        driver.set(new ChromeDriver(capabilities) {
            @Override
            public WebElement findElement(By by) {
                try {
                    return by.findElement(this);
                } catch (NoSuchElementException nse) {
                    Field f = null;
                    try {
                        f = Throwable.class.getDeclaredField("detailMessage");
                    } catch (NoSuchFieldException e) {
                        throw nse;
                    }
                    f.setAccessible(true);
                    try {
                        String error = "\n" + by.toString() + "\n" + f.get(nse);
                        f.set(nse, error);
                    } catch (IllegalAccessException ignored) {
                    }
                    throw nse;
                }
            }
        });
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void setupRemoteDriver(String hubUrl, String platformName, String browser, String version) throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setVersion(version);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        driver.set(new RemoteWebDriver(URI.create(hubUrl).toURL(), capabilities));
    }

    @BeforeMethod
    public void startBrowser() throws IOException {
        System.setProperty("baseurl","http://test-app.d6.dev.devcaz.com/admin/login");
        String message = "* Starting test " + this.getClass().toString();
        Reporter.log("\n" + message);
        System.out.println(message);

        String hubUrl = System.getProperty("hub");
        String browser = System.getProperty("browser" ,"chrome");
        String version = System.getProperty("version", "103.0");
        String platform = System.getProperty("sun.desktop"); //or  os.name

        if (browser.equalsIgnoreCase("chrome")) {
            if (hubUrl != null && !hubUrl.isEmpty()) {
                this.setupRemoteDriver(hubUrl, platform, browser, version);
            } else {
                this.startChrome();
            }

        }
        getDriver().manage().window().setSize(new Dimension(1920, 1080));

        Reporter.log("Current window size is: " + getDriver().manage().window().getSize());
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        getDriver().quit();
    }

    @AfterClass(alwaysRun = true)
    public void terminate() {
        //Remove the ThreadLocalMap element
        driver.remove();
    }
}