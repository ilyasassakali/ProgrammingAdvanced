package be.ehb.euromoon.model.ticket;

import be.ehb.euromoon.model.journey.Journey;
import be.ehb.euromoon.model.person.Passenger;

import java.util.UUID;

/**
 * Represents a ticket purchased by a passenger for a journey.
 *
 * <p>A ticket links a passenger to a specific journey and class type.
 * It serves as proof of purchase and reservation for a seat on the train.
 * Each ticket has a unique identifier for tracking and validation purposes.
 */
public class Ticket {
    private UUID id;
    private Passenger passenger;
    private Journey journey;
    private ClassType classType;

    public Ticket(Passenger passenger, Journey journey, ClassType classType) {
        this.id = UUID.randomUUID();
        this.passenger = passenger;
        this.journey = journey;
        this.classType = classType;
    }

    public UUID getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    @Override
    public String toString() {
        return "Ticket for " + passenger.getName() + " " + passenger.getLastname() +
               " - " + journey.getDepartureStation() + " to " + journey.getArrivalStation() +
               " (" + classType + " class)";
    }
}
