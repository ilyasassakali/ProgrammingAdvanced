package be.ehb.euromoon.model.person.personnel;

import java.time.LocalDate;

/**
 * Represents a conductor (train driver) in the Euromoon railway system.
 *
 * <p>Conductors are responsible for operating trains and ensuring safe travel.
 * They require specific certifications such as driver's licenses for
 * operating railway vehicles.
 */
public class Conductor extends Personnel {

    public Conductor(String name, String lastname, String rijksregisternummer, LocalDate geboortedatum) {
        super(name, lastname, rijksregisternummer, geboortedatum);
    }
}
