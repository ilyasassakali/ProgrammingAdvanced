package be.ehb.euromoon.model.train;

import be.ehb.euromoon.exception.InvalidTrainException;
import be.ehb.euromoon.model.ticket.ClassType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complete train in the Euromoon railway system.
 *
 * <p>A train consists of a locomotive and a list of wagons. The number
 * of wagons is limited by the locomotive's maximum capacity. The train
 * tracks its total passenger capacity and available seats by class type.
 */
public class Train {
    private String trainId;
    private Locomotive locomotive;
    private List<Wagon> wagons;

    public Train(String trainId, Locomotive locomotive) {
        this.trainId = trainId;
        this.locomotive = locomotive;
        this.wagons = new ArrayList<>();
    }

    /**
     * Adds a wagon to this train.
     *
     * <p>The wagon can only be added if the train has not reached its
     * maximum wagon capacity as defined by the locomotive type.
     *
     * @param wagon the wagon to add
     * @throws InvalidTrainException if adding the wagon would exceed the maximum
     */
    public void addWagon(Wagon wagon) throws InvalidTrainException {
        if (wagons.size() >= locomotive.getMaxWagons()) {
            throw new InvalidTrainException(
                "Cannot add wagon: " + locomotive.getLocomotiveType().getTypeName() +
                " supports maximum " + locomotive.getMaxWagons() + " wagons"
            );
        }
        wagons.add(wagon);
    }

    /**
     * Calculates the total passenger capacity of this train.
     *
     * @return the total number of seats available
     */
    public int totalCapacity() {
        int wagonCapacity = wagons.stream()
            .mapToInt(Wagon::getSeats)
            .sum();
        return locomotive.getCapacity() + wagonCapacity;
    }

    /**
     * Calculates the available seats for a specific class type.
     *
     * @param classType the class type to check
     * @return the number of seats available for the specified class
     */
    public int getAvailableSeats(ClassType classType) {
        return wagons.stream()
            .filter(w -> w.getClassType() == classType)
            .mapToInt(Wagon::getSeats)
            .sum();
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public List<Wagon> getWagons() {
        return new ArrayList<>(wagons);
    }

    @Override
    public String toString() {
        return "Train " + trainId + " (" + locomotive.getLocomotiveType().getTypeName() +
               ", " + wagons.size() + " wagons, " + totalCapacity() + " seats)";
    }
}
