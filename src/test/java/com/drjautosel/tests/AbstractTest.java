package com.drjautosel.tests;

import com.drjautosel.listener.TestListener;
import com.drjautosel.util.Config;
import com.drjautosel.util.Constants;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public abstract class AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext context) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))? getRemoteDriver(): getLocalDriver();
        context.setAttribute(Constants.DRIVER, this.driver);
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {

        Capabilities capabilities = new ChromeOptions();

        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        }

        String urlFormat = Config.get(Constants.GRID_URLFORMAT);
        String hubhost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat,hubhost);
        log.info("grid url {}", url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();

        return  new ChromeDriver();
    }

    @AfterTest
    public void quitBrowser() {
        this.driver.quit();
    }
}
