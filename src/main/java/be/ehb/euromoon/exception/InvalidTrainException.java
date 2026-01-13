package be.ehb.euromoon.exception;

/**
 * Exception thrown when train configuration is invalid.
 *
 * <p>This exception is thrown when attempting operations that would violate
 * train constraints, such as adding too many wagons beyond the locomotive's
 * maximum capacity.
 */
public class InvalidTrainException extends Exception {

    public InvalidTrainException(String message) {
        super(message);
    }

    public InvalidTrainException(String message, Throwable cause) {
        super(message, cause);
    }
}
