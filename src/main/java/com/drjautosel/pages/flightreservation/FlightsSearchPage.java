package com.drjautosel.pages.flightreservation;

import com.drjautosel.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightsSearchPage extends AbstractPage {


    public FlightsSearchPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersSelect));

        return this.passengersSelect.isDisplayed();
    }

    @FindBy(css="#passengers")
    private WebElement passengersSelect;

    @FindBy(css="#search-flights")
    private WebElement searchFlightsBtn;

    public void selectPassengers(String passText){
        Select oSelect = new Select(this.passengersSelect);
        oSelect.selectByVisibleText(passText);
    }

    public void searchFlights(){
        this.searchFlightsBtn.click();
    }
}
