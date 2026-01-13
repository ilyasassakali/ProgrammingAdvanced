package be.ehb.euromoon.repository;

import be.ehb.euromoon.model.person.personnel.Personnel;

import java.util.*;

/**
 * Repository for managing personnel in memory.
 *
 * <p>This repository stores personnel members using their rijksregisternummer
 * as a unique identifier. It provides methods for saving, finding, and
 * managing personnel records.
 */
public class PersonnelRepository {
    private final Map<String, Personnel> personnel = new HashMap<>();

    /**
     * Saves a personnel member to the repository.
     *
     * @param person the personnel member to save
     */
    public void save(Personnel person) {
        if (person != null && person.getRijksregisternummer() != null) {
            personnel.put(person.getRijksregisternummer(), person);
        }
    }

    /**
     * Finds a personnel member by rijksregisternummer.
     *
     * @param rijksregisternummer the rijksregisternummer to search for
     * @return an Optional containing the personnel if found, empty otherwise
     */
    public Optional<Personnel> findByRijksregisternummer(String rijksregisternummer) {
        return Optional.ofNullable(personnel.get(rijksregisternummer));
    }

    /**
     * Returns all personnel in the repository.
     *
     * @return a list of all personnel
     */
    public List<Personnel> findAll() {
        return new ArrayList<>(personnel.values());
    }

    /**
     * Checks if a personnel member exists with the given rijksregisternummer.
     *
     * @param rijksregisternummer the rijksregisternummer to check
     * @return true if a personnel member exists, false otherwise
     */
    public boolean exists(String rijksregisternummer) {
        return personnel.containsKey(rijksregisternummer);
    }
}
