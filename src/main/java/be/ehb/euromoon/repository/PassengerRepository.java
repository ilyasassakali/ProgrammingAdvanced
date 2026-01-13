package be.ehb.euromoon.repository;

import be.ehb.euromoon.model.person.Passenger;

import java.util.*;

/**
 * Repository for managing passengers in memory.
 *
 * <p>This repository stores passengers using their rijksregisternummer as
 * a unique identifier. It provides methods for saving, finding, and
 * checking existence of passengers.
 */
public class PassengerRepository {
    private final Map<String, Passenger> passengers = new HashMap<>();

    /**
     * Saves a passenger to the repository.
     *
     * @param passenger the passenger to save
     */
    public void save(Passenger passenger) {
        if (passenger != null && passenger.getRijksregisternummer() != null) {
            passengers.put(passenger.getRijksregisternummer(), passenger);
        }
    }

    /**
     * Finds a passenger by rijksregisternummer.
     *
     * @param rijksregisternummer the rijksregisternummer to search for
     * @return an Optional containing the passenger if found, empty otherwise
     */
    public Optional<Passenger> findByRijksregisternummer(String rijksregisternummer) {
        return Optional.ofNullable(passengers.get(rijksregisternummer));
    }

    /**
     * Returns all passengers in the repository.
     *
     * @return a list of all passengers
     */
    public List<Passenger> findAll() {
        return new ArrayList<>(passengers.values());
    }

    /**
     * Checks if a passenger exists with the given rijksregisternummer.
     *
     * @param rijksregisternummer the rijksregisternummer to check
     * @return true if a passenger exists, false otherwise
     */
    public boolean exists(String rijksregisternummer) {
        return passengers.containsKey(rijksregisternummer);
    }
}
