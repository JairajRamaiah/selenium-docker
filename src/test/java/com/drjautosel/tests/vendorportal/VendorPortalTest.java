package com.drjautosel.tests.vendorportal;

import com.drjautosel.pages.vendorportal.DashboardPage;
import com.drjautosel.pages.vendorportal.LoginPage;
import com.drjautosel.tests.AbstractTest;
import com.drjautosel.tests.vendorportal.model.VendorPortalTestData;
import com.drjautosel.util.Config;
import com.drjautosel.util.Constants;
import com.drjautosel.util.JasonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class VendorPortalTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(VendorPortalTest.class);

    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private VendorPortalTestData testData;



    @BeforeTest
    @Parameters("testDataPath")
    public void setPages(String testDataPath) throws IOException {
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        this.testData = JasonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest() {

        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL));
        Assert.assertTrue(loginPage.isAt());

        loginPage.loginToVendorPortal(this.testData.username(), this.testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() {

        Assert.assertTrue(dashboardPage.isAt());

        Assert.assertEquals(dashboardPage.getMonthlyEarnings(), this.testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarnings(), this.testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), this.testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), this.testData.availableInventory());

        dashboardPage.searchOrderHistory("adams");
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), this.testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest() {
        dashboardPage.logout();
    }
}
