package be.ehb.euromoon.model.train;

/**
 * Interface representing a type of locomotive in the Euromoon railway system.
 *
 * <p>This interface uses the Strategy pattern to allow for different
 * locomotive types with varying specifications. New locomotive types
 * can be added by implementing this interface without modifying
 * existing code, following the Open/Closed Principle.
 */
public interface LocomotiveType {

    /**
     * Returns the maximum number of wagons this locomotive type can pull.
     *
     * @return the maximum number of wagons
     */
    int getMaxWagons();

    /**
     * Returns the name of this locomotive type.
     *
     * @return the locomotive type name
     */
    String getTypeName();

    /**
     * Returns the passenger capacity of the locomotive itself.
     *
     * @return the locomotive capacity
     */
    int getCapacity();
}
