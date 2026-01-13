package be.ehb.euromoon.model.train;

/**
 * Represents a Class 374 locomotive type.
 *
 * <p>This locomotive type can pull a maximum of 14 wagons and has
 * a capacity of 80 passengers with baggage. It supports both first
 * and second class seating.
 */
public class Class374 implements LocomotiveType {
    private static final int MAX_WAGONS = 14;
    private static final int CAPACITY = 80;
    private static final String TYPE_NAME = "Class 374";

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
