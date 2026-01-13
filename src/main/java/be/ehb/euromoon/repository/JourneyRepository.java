package be.ehb.euromoon.repository;

import be.ehb.euromoon.model.journey.Journey;

import java.util.*;

/**
 * Repository for managing journeys in memory.
 *
 * <p>This repository stores journeys using their UUID as a unique identifier.
 * It provides methods for saving, finding, and managing journey records.
 */
public class JourneyRepository {
    private final Map<UUID, Journey> journeys = new HashMap<>();

    /**
     * Saves a journey to the repository.
     *
     * @param journey the journey to save
     */
    public void save(Journey journey) {
        if (journey != null && journey.getId() != null) {
            journeys.put(journey.getId(), journey);
        }
    }

    /**
     * Finds a journey by its ID.
     *
     * @param id the journey ID to search for
     * @return an Optional containing the journey if found, empty otherwise
     */
    public Optional<Journey> findById(UUID id) {
        return Optional.ofNullable(journeys.get(id));
    }

    /**
     * Returns all journeys in the repository.
     *
     * @return a list of all journeys
     */
    public List<Journey> findAll() {
        return new ArrayList<>(journeys.values());
    }

    /**
     * Checks if a journey exists with the given ID.
     *
     * @param id the journey ID to check
     * @return true if a journey exists, false otherwise
     */
    public boolean exists(UUID id) {
        return journeys.containsKey(id);
    }
}
