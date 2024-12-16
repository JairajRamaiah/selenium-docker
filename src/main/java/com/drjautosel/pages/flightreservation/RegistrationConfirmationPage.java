package com.drjautosel.pages.flightreservation;

import com.drjautosel.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css="#registration-confirmation-section p b")
    private WebElement nameText;


    @FindBy(css="#go-to-flights-search")
    private WebElement searchFlightElement;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.searchFlightElement));

        return this.searchFlightElement.isDisplayed();
    }

    public void goToFlightSearch(){
        this.searchFlightElement.click();
    }

    public String getNameConfirmation(){
       return this.nameText.getText();
    }
}
