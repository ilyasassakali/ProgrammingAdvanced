package be.ehb.euromoon.model.person.personnel;

import java.time.LocalDate;

/**
 * Represents a steward in the Euromoon railway system.
 *
 * <p>Stewards are responsible for passenger service, safety, and comfort
 * during train journeys. They assist passengers, provide information,
 * and ensure safety protocols are followed.
 */
public class Steward extends Personnel {

    public Steward(String name, String lastname, String rijksregisternummer, LocalDate geboortedatum) {
        super(name, lastname, rijksregisternummer, geboortedatum);
    }
}
