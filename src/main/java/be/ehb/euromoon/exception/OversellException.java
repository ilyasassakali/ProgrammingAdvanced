package be.ehb.euromoon.exception;

/**
 * Exception thrown when attempting to sell more tickets than available capacity.
 *
 * <p>This exception is thrown by the ticket service when a ticket sale would
 * exceed the available seats on a journey for a specific class type.
 */
public class OversellException extends Exception {

    public OversellException(String message) {
        super(message);
    }

    public OversellException(String message, Throwable cause) {
        super(message, cause);
    }
}
