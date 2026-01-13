package be.ehb.euromoon.service;

import be.ehb.euromoon.exception.InvalidPersonnelException;
import be.ehb.euromoon.exception.ValidationException;
import be.ehb.euromoon.model.journey.Journey;
import be.ehb.euromoon.model.person.personnel.Personnel;
import be.ehb.euromoon.model.train.Train;
import be.ehb.euromoon.repository.JourneyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Service for managing journey operations.
 *
 * <p>This service handles journey creation, train assignment, personnel assignment,
 * and validation of journey requirements. It ensures that journeys have the
 * necessary resources and personnel before they can operate.
 */
public class JourneyService {
    private final JourneyRepository journeyRepository;

    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    /**
     * Creates a new journey.
     *
     * @param departureStation the departure station name
     * @param arrivalStation the arrival station name
     * @param departureTime the scheduled departure time
     * @return the created journey
     * @throws ValidationException if validation fails
     */
    public Journey createJourney(String departureStation, String arrivalStation,
                                 LocalDateTime departureTime) throws ValidationException {
        if (departureStation == null || departureStation.trim().isEmpty()) {
            throw new ValidationException("Departure station cannot be empty");
        }
        if (arrivalStation == null || arrivalStation.trim().isEmpty()) {
            throw new ValidationException("Arrival station cannot be empty");
        }
        if (departureTime == null) {
            throw new ValidationException("Departure time cannot be null");
        }
        if (departureTime.isBefore(LocalDateTime.now())) {
            throw new ValidationException("Departure time cannot be in the past");
        }

        Journey journey = new Journey(departureStation, arrivalStation, departureTime);
        journeyRepository.save(journey);

        return journey;
    }

    /**
     * Assigns a train to a journey.
     *
     * @param journeyId the journey ID
     * @param train the train to assign
     * @throws ValidationException if journey not found or train is null
     */
    public void assignTrainToJourney(UUID journeyId, Train train) throws ValidationException {
        if (train == null) {
            throw new ValidationException("Train cannot be null");
        }

        Journey journey = journeyRepository.findById(journeyId)
            .orElseThrow(() -> new ValidationException("Journey not found: " + journeyId));

        journey.assignTrain(train);
        journeyRepository.save(journey);
    }

    /**
     * Assigns personnel to a journey.
     *
     * @param journeyId the journey ID
     * @param personnel the personnel to assign
     * @throws ValidationException if journey not found or personnel is null
     */
    public void assignPersonnelToJourney(UUID journeyId, Personnel personnel) throws ValidationException {
        if (personnel == null) {
            throw new ValidationException("Personnel cannot be null");
        }

        Journey journey = journeyRepository.findById(journeyId)
            .orElseThrow(() -> new ValidationException("Journey not found: " + journeyId));

        journey.assignPersonnel(personnel);
        journeyRepository.save(journey);
    }

    /**
     * Validates that a journey meets all requirements for operation.
     *
     * @param journeyId the journey ID to validate
     * @throws ValidationException if journey not found
     * @throws InvalidPersonnelException if personnel requirements not met
     */
    public void validateJourney(UUID journeyId) throws ValidationException, InvalidPersonnelException {
        Journey journey = journeyRepository.findById(journeyId)
            .orElseThrow(() -> new ValidationException("Journey not found: " + journeyId));

        if (journey.getTrain() == null) {
            throw new ValidationException("Journey must have an assigned train");
        }

        if (!journey.validatePersonnel()) {
            throw new InvalidPersonnelException(
                "Journey requires at least 1 conductor and 3 stewards. " +
                "Currently assigned: " + journey.getAssignedPersonnel().size() + " personnel"
            );
        }
    }

    /**
     * Finds a journey by its ID.
     *
     * @param journeyId the journey ID to search for
     * @return the journey if found
     * @throws ValidationException if journey not found
     */
    public Journey findById(UUID journeyId) throws ValidationException {
        return journeyRepository.findById(journeyId)
            .orElseThrow(() -> new ValidationException("Journey not found: " + journeyId));
    }

    /**
     * Returns all journeys in the system.
     *
     * @return a list of all journeys
     */
    public List<Journey> getAllJourneys() {
        return journeyRepository.findAll();
    }
}
