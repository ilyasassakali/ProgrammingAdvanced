package be.ehb.euromoon.model.journey;

import be.ehb.euromoon.model.person.personnel.Conductor;
import be.ehb.euromoon.model.person.personnel.Personnel;
import be.ehb.euromoon.model.person.personnel.Steward;
import be.ehb.euromoon.model.ticket.ClassType;
import be.ehb.euromoon.model.train.Train;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a train journey in the Euromoon railway system.
 *
 * <p>A journey is a scheduled trip between two stations at a specific time.
 * It requires an assigned train and personnel (minimum 1 conductor and 3 stewards)
 * to operate. The journey tracks its assigned personnel and provides capacity
 * information for ticket sales.
 */
public class Journey {
    private UUID id;
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureTime;
    private Train train;
    private List<Personnel> assignedPersonnel;

    public Journey(String departureStation, String arrivalStation, LocalDateTime departureTime) {
        this.id = UUID.randomUUID();
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.assignedPersonnel = new ArrayList<>();
    }

    /**
     * Assigns a train to this journey.
     *
     * @param train the train to assign
     */
    public void assignTrain(Train train) {
        this.train = train;
    }

    /**
     * Assigns a personnel member to this journey.
     *
     * @param personnel the personnel to assign
     */
    public void assignPersonnel(Personnel personnel) {
        if (personnel != null) {
            assignedPersonnel.add(personnel);
        }
    }

    /**
     * Validates that this journey has the minimum required personnel.
     *
     * <p>A journey requires at least 1 conductor (driver) and 3 stewards
     * to operate safely and provide adequate passenger service.
     *
     * @return true if personnel requirements are met, false otherwise
     */
    public boolean validatePersonnel() {
        long conductorCount = assignedPersonnel.stream()
            .filter(p -> p instanceof Conductor)
            .count();

        long stewardCount = assignedPersonnel.stream()
            .filter(p -> p instanceof Steward)
            .count();

        return conductorCount >= 1 && stewardCount >= 3;
    }

    /**
     * Returns the available seats for a specific class type.
     *
     * <p>This delegates to the assigned train to calculate capacity.
     * Returns 0 if no train is assigned.
     *
     * @param classType the class type to check
     * @return the number of available seats
     */
    public int getAvailableSeats(ClassType classType) {
        if (train == null) {
            return 0;
        }
        return train.getAvailableSeats(classType);
    }

    public UUID getId() {
        return id;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Personnel> getAssignedPersonnel() {
        return new ArrayList<>(assignedPersonnel);
    }

    @Override
    public String toString() {
        return departureStation + " -> " + arrivalStation +
               " at " + departureTime +
               (train != null ? " (" + train.getTrainId() + ")" : " (no train assigned)");
    }
}
