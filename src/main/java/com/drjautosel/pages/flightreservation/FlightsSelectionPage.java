package com.drjautosel.pages.flightreservation;

import com.drjautosel.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage extends AbstractPage {

    public FlightsSelectionPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css="input[name='departure-flight']")
    private List<WebElement> departureFlightsOptionsEle;

    @FindBy(css="input[name='arrival-flight']")
    private List<WebElement> arrivalFlightOptionsEle;

    @FindBy(css="#confirm-flights")
    private WebElement confirmFlightButton;


    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightButton));
        return this.confirmFlightButton.isDisplayed();
    }

    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0, this.departureFlightsOptionsEle.size());
        this.departureFlightsOptionsEle.get(random).click();
        this.arrivalFlightOptionsEle.get(random).click();
    }

    public void confirmFlights(){
        this.confirmFlightButton.click();
    }

}
