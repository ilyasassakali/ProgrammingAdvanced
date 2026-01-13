package be.ehb.euromoon.model.train;

import be.ehb.euromoon.model.ticket.ClassType;

/**
 * Represents a wagon (train car) in the Euromoon railway system.
 *
 * <p>Wagons are attached to locomotives to form complete trains.
 * Each wagon has a specific class type and number of seats available
 * for passengers.
 */
public class Wagon {
    private int wagonNumber;
    private ClassType classType;
    private int seats;

    public Wagon(int wagonNumber, ClassType classType, int seats) {
        this.wagonNumber = wagonNumber;
        this.classType = classType;
        this.seats = seats;
    }

    public int getWagonNumber() {
        return wagonNumber;
    }

    public void setWagonNumber(int wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Wagon " + wagonNumber + " (" + classType + " class, " + seats + " seats)";
    }
}
