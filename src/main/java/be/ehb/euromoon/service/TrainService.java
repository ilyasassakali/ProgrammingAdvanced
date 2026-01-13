package be.ehb.euromoon.service;

import be.ehb.euromoon.exception.InvalidTrainException;
import be.ehb.euromoon.exception.ValidationException;
import be.ehb.euromoon.model.ticket.ClassType;
import be.ehb.euromoon.model.train.*;
import be.ehb.euromoon.repository.TrainRepository;

import java.util.List;

/**
 * Service for managing train operations.
 *
 * <p>This service handles train creation, wagon management, and train retrieval.
 * It ensures that trains are properly configured and validates wagon limits
 * according to locomotive specifications.
 */
public class TrainService {
    private final TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    /**
     * Creates a new train with the specified locomotive type.
     *
     * @param trainId the unique identifier for the train
     * @param locomotiveType the type of locomotive
     * @return the created train
     * @throws ValidationException if validation fails
     */
    public Train createTrain(String trainId, LocomotiveType locomotiveType) throws ValidationException {
        if (trainId == null || trainId.trim().isEmpty()) {
            throw new ValidationException("Train ID cannot be empty");
        }
        if (locomotiveType == null) {
            throw new ValidationException("Locomotive type cannot be null");
        }
        if (trainRepository.exists(trainId)) {
            throw new ValidationException("A train with ID " + trainId + " already exists");
        }

        Locomotive locomotive = new Locomotive(locomotiveType);
        Train train = new Train(trainId, locomotive);
        trainRepository.save(train);

        return train;
    }

    /**
     * Adds a wagon to an existing train.
     *
     * @param trainId the ID of the train
     * @param wagonNumber the wagon number
     * @param classType the class type of the wagon
     * @param seats the number of seats in the wagon
     * @throws ValidationException if train not found
     * @throws InvalidTrainException if wagon cannot be added
     */
    public void addWagonToTrain(String trainId, int wagonNumber, ClassType classType, int seats)
            throws ValidationException, InvalidTrainException {
        Train train = trainRepository.findById(trainId)
            .orElseThrow(() -> new ValidationException("Train not found: " + trainId));

        if (seats <= 0) {
            throw new ValidationException("Seats must be greater than 0");
        }

        Wagon wagon = new Wagon(wagonNumber, classType, seats);
        train.addWagon(wagon);
        trainRepository.save(train);
    }

    /**
     * Finds a train by its ID.
     *
     * @param trainId the train ID to search for
     * @return the train if found
     * @throws ValidationException if train not found
     */
    public Train findById(String trainId) throws ValidationException {
        return trainRepository.findById(trainId)
            .orElseThrow(() -> new ValidationException("Train not found: " + trainId));
    }

    /**
     * Returns all trains in the system.
     *
     * @return a list of all trains
     */
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }
}
