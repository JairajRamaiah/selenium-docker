package com.drjautosel.tests.flightreservation;

import com.drjautosel.pages.flightreservation.*;
import com.drjautosel.tests.AbstractTest;
import com.drjautosel.tests.flightreservation.model.FlightReservationTestData;
import com.drjautosel.util.Config;
import com.drjautosel.util.Constants;
import com.drjautosel.util.JasonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;


    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) throws IOException {
        this.testData = JasonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));

        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(this.testData.firstname(), this.testData.lastname());
        registrationPage.enterUserCredentials(this.testData.email(), this.testData.password());
        registrationPage.enterAddress(this.testData.city(), this.testData.state(), this.testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);

        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getNameConfirmation(), testData.firstname());

        registrationConfirmationPage.goToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);

        Assert.assertTrue(flightsSearchPage.isAt());

        flightsSearchPage.selectPassengers(this.testData.passengersCount());

        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void selectionFlights(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);

        Assert.assertTrue(flightsSelectionPage.isAt());

        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "selectionFlights")
    public void flightsConfirmationTest(){
            FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
            Assert.assertTrue(flightConfirmationPage.isAt());
            Assert.assertEquals(flightConfirmationPage.getPrice(),this.testData.expectedPrice());
    }
}
