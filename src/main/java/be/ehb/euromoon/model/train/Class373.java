package be.ehb.euromoon.model.train;

/**
 * Represents a Class 373 locomotive type.
 *
 * <p>This locomotive type can pull a maximum of 12 wagons and has
 * a capacity of 80 passengers with baggage. It supports both first
 * and second class seating.
 */
public class Class373 implements LocomotiveType {
    private static final int MAX_WAGONS = 12;
    private static final int CAPACITY = 80;
    private static final String TYPE_NAME = "Class 373";

    @Override
    public int getMaxWagons() {
        return MAX_WAGONS;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }
}
