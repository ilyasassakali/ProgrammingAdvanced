package be.ehb.euromoon.repository;

import be.ehb.euromoon.model.train.Train;

import java.util.*;

/**
 * Repository for managing trains in memory.
 *
 * <p>This repository stores trains using their train ID as a unique identifier.
 * It provides methods for saving, finding, and managing train records.
 */
public class TrainRepository {
    private final Map<String, Train> trains = new HashMap<>();

    /**
     * Saves a train to the repository.
     *
     * @param train the train to save
     */
    public void save(Train train) {
        if (train != null && train.getTrainId() != null) {
            trains.put(train.getTrainId(), train);
        }
    }

    /**
     * Finds a train by its ID.
     *
     * @param trainId the train ID to search for
     * @return an Optional containing the train if found, empty otherwise
     */
    public Optional<Train> findById(String trainId) {
        return Optional.ofNullable(trains.get(trainId));
    }

    /**
     * Returns all trains in the repository.
     *
     * @return a list of all trains
     */
    public List<Train> findAll() {
        return new ArrayList<>(trains.values());
    }

    /**
     * Checks if a train exists with the given ID.
     *
     * @param trainId the train ID to check
     * @return true if a train exists, false otherwise
     */
    public boolean exists(String trainId) {
        return trains.containsKey(trainId);
    }
}
