package be.ehb.euromoon.model.train;

/**
 * Represents a locomotive in the Euromoon railway system.
 *
 * <p>A locomotive is the powered railway vehicle that pulls the wagons
 * of a train. It has a specific type that determines its capabilities,
 * including maximum wagon capacity and passenger capacity.
 */
public class Locomotive {
    private LocomotiveType locomotiveType;

    public Locomotive(LocomotiveType locomotiveType) {
        this.locomotiveType = locomotiveType;
    }

    /**
     * Returns the maximum number of wagons this locomotive can pull.
     *
     * @return the maximum number of wagons
     */
    public int getMaxWagons() {
        return locomotiveType.getMaxWagons();
    }

    /**
     * Returns the passenger capacity of this locomotive.
     *
     * @return the locomotive capacity
     */
    public int getCapacity() {
        return locomotiveType.getCapacity();
    }

    public LocomotiveType getLocomotiveType() {
        return locomotiveType;
    }

    public void setLocomotiveType(LocomotiveType locomotiveType) {
        this.locomotiveType = locomotiveType;
    }

    @Override
    public String toString() {
        return locomotiveType.getTypeName();
    }
}
