package com.drjautosel.pages.flightreservation;

import com.drjautosel.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends AbstractPage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.registerButton));
        return this.registerButton.isDisplayed();
    }

    @FindBy(css = "#firstName")
    private WebElement inputFirstName;

    @FindBy(css = "#lastName")
    private WebElement inputLastName;

    @FindBy(css = "#email")
    private WebElement inputEmail;

    @FindBy(css = "#password")
    private WebElement inputPassword;

    @FindBy(css = "#street")
    private WebElement inputStreet;

    @FindBy(css = "input[name='city']")
    private WebElement inputCity;

    @FindBy(css = "input[name='zip']")
    private WebElement inputZip;

    @FindBy(css = "#register-btn")
    private WebElement registerButton;

    @FindBy(css = "#inputState")
    private WebElement selectElement;


    public void selectValueByVisibleText(String visibleText) {
        selectElement.click();
        Select oSelect = new Select(selectElement);

        oSelect.selectByVisibleText(visibleText);
    }

    public void enterUserDetails(String firstName, String lastName) {
        this.inputFirstName.sendKeys(firstName);
        this.inputLastName.sendKeys(lastName);
    }

    public void enterUserCredentials(String email, String password){
        this.inputEmail.sendKeys(email);
        this.inputPassword.sendKeys(password);
    }

    public void enterAddress(String city, String state, String zip){
        this.inputCity.sendKeys(city);
        this.selectValueByVisibleText(state);
        this.inputZip.sendKeys(zip);
    }

    public void register() {
        this.registerButton.click();
    }

    public  void goTo(String url){
        this.driver.get(url);
    }
}
