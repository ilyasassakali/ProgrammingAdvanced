package be.ehb.euromoon.model.person;

import java.time.LocalDate;

/**
 * Represents a passenger in the Euromoon railway system.
 *
 * <p>A passenger is a person who purchases tickets and travels on trains.
 * This class extends the Person class without adding additional fields,
 * serving primarily to distinguish passengers from personnel.
 */
public class Passenger extends Person {

    public Passenger(String name, String lastname, String rijksregisternummer, LocalDate geboortedatum) {
        super(name, lastname, rijksregisternummer, geboortedatum);
    }
}
