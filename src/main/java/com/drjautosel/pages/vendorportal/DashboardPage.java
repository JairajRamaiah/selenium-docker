package com.drjautosel.pages.vendorportal;

import com.drjautosel.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {
    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css="#monthly-earning")
    private WebElement monthlyEarningEle;

    @FindBy(css="#annual-earning")
    private WebElement annualEarningEle;

    @FindBy(css="#profit-margin")
    private WebElement profitMarginEle;

    @FindBy(css="#available-inventory")
    private WebElement availableInvEle;

    @FindBy(css="#dataTable_filter input[type='search']")
    private WebElement searchHistoryEle;

    @FindBy(css="#dataTable_info")
    private WebElement seacrhResultsCountEle;

    @FindBy(css="#userDropdown img.img-profile")
    private WebElement imageProfileEle;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css="#logoutModal a.btn-primary")
    private  WebElement logoutModalButton;

    public String getMonthlyEarnings(){
        return this.monthlyEarningEle.getText();
    }

    public String getAnnualEarnings(){
       return this.annualEarningEle.getText();
    }

    public String getProfitMargin(){
        return this.profitMarginEle.getText();
    }

    public String getAvailableInventory(){
        return this.availableInvEle.getText();
    }

    public void searchOrderHistory(String inputSearchStr){
        this.searchHistoryEle.sendKeys(inputSearchStr);
    }

    public int getSearchResultsCount(){

        String searchText = this.seacrhResultsCountEle.getText();
        String[] strArr = searchText.split(" ");
        int count = Integer.parseInt(strArr[5]);

        log.info("Search Results Count : {}", count);

        return count;
    }

    public void logout(){
        this.imageProfileEle.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutModalButton));
        this.logoutModalButton.click();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.seacrhResultsCountEle));
        return this.seacrhResultsCountEle.isDisplayed();
    }
}
