package be.ehb.euromoon.service;

import be.ehb.euromoon.exception.ValidationException;
import be.ehb.euromoon.model.person.Passenger;
import be.ehb.euromoon.repository.PassengerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
 * Service for managing passenger operations.
 *
 * <p>This service handles passenger registration, validation, and retrieval.
 * It ensures that passengers have valid information and unique
 * rijksregisternummer before being saved to the repository.
 */
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final Random random;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
        this.random = new Random();
    }

    /**
     * Registers a new passenger in the system.
     *
     * @param name the passenger's first name
     * @param lastname the passenger's last name
     * @param rijksregisternummer the passenger's national registry number
     * @param geboortedatum the passenger's birth date
     * @return the registered passenger
     * @throws ValidationException if validation fails
     */
    public Passenger registerPassenger(String name, String lastname, String rijksregisternummer,
                                       LocalDate geboortedatum) throws ValidationException {
        validatePassengerData(name, lastname, rijksregisternummer, geboortedatum);

        if (passengerRepository.exists(rijksregisternummer)) {
            throw new ValidationException(
                "A passenger with rijksregisternummer " + rijksregisternummer + " already exists"
            );
        }

        String processedName = applyNameProcessing(name);
        Passenger passenger = new Passenger(processedName, lastname, rijksregisternummer, geboortedatum);
        passengerRepository.save(passenger);

        return passenger;
    }

    /**
     * Finds a passenger by rijksregisternummer.
     *
     * @param rijksregisternummer the rijksregisternummer to search for
     * @return the passenger if found
     * @throws ValidationException if passenger not found
     */
    public Passenger findByRijksregisternummer(String rijksregisternummer) throws ValidationException {
        return passengerRepository.findByRijksregisternummer(rijksregisternummer)
            .orElseThrow(() -> new ValidationException(
                "No passenger found with rijksregisternummer: " + rijksregisternummer
            ));
    }

    /**
     * Returns all registered passengers.
     *
     * @return a list of all passengers
     */
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    private void validatePassengerData(String name, String lastname, String rijksregisternummer,
                                       LocalDate geboortedatum) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (lastname == null || lastname.trim().isEmpty()) {
            throw new ValidationException("Lastname cannot be empty");
        }
        if (rijksregisternummer == null || rijksregisternummer.trim().isEmpty()) {
            throw new ValidationException("Rijksregisternummer cannot be empty");
        }
        if (geboortedatum == null) {
            throw new ValidationException("Geboortedatum cannot be null");
        }
        if (geboortedatum.isAfter(LocalDate.now())) {
            throw new ValidationException("Geboortedatum cannot be in the future");
        }
    }

    private String applyNameProcessing(String name) {
        if (random.nextInt(4) == 0) {
            return "verdacht " + name;
        }
        return name;
    }
}
