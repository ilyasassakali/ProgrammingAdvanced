package be.ehb.euromoon.model.person.personnel;

import java.time.LocalDate;

/**
 * Represents baggage personnel in the Euromoon railway system.
 *
 * <p>Baggage personnel are responsible for handling, loading, and unloading
 * passenger luggage. They ensure safe and secure baggage transport
 * throughout the journey.
 */
public class BaggagePersonnel extends Personnel {

    public BaggagePersonnel(String name, String lastname, String rijksregisternummer, LocalDate geboortedatum) {
        super(name, lastname, rijksregisternummer, geboortedatum);
    }
}
