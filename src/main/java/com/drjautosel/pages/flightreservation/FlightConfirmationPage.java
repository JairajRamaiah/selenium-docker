package com.drjautosel.pages.flightreservation;

import com.drjautosel.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2) > p")
    private WebElement flightConfirmationEle;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2) > p")
    private WebElement totalPriceEle;

    @Override
    public boolean isAt() {

        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationEle));
        return this.flightConfirmationEle.isDisplayed();
    }

    public String getPrice() {
        String confirmation = this.flightConfirmationEle.getText();
        String totalPrice = this.totalPriceEle.getText();

        log.info("Flight Confirmation : {}", confirmation);
        log.info("Total Price : {}", totalPrice);

        return totalPrice;
    }
}
