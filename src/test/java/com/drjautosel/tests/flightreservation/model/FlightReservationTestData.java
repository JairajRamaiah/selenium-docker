package com.drjautosel.tests.flightreservation.model;

public record FlightReservationTestData(String firstname,
                                        String lastname,
                                        String email,
                                        String password,
                                        String city,
                                        String state,
                                        String zip,
                                        String passengersCount,
                                        String expectedPrice) {
}
