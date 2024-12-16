package com.drjautosel.pages.vendorportal;

import com.drjautosel.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css="#username")
    private WebElement inputUsername;

    @FindBy(css="#password")
    private WebElement inputpassword;

    @FindBy(css="#login")
    private WebElement loginButton;

    public void goTo(String url){
        this.driver.get(url);
    }

    public void loginToVendorPortal(String username, String password){
        this.inputUsername.sendKeys(username);
        this.inputpassword.sendKeys(password);
        this.loginButton.click();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.loginButton));
        return this.loginButton.isDisplayed();
    }
}
