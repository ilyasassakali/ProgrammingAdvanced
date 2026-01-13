package be.ehb.euromoon.exception;

/**
 * Exception thrown when a journey has invalid or insufficient personnel.
 *
 * <p>This exception is thrown when a journey does not meet the minimum
 * personnel requirements (at least 1 conductor and 3 stewards) or when
 * personnel assignments are invalid.
 */
public class InvalidPersonnelException extends Exception {

    public InvalidPersonnelException(String message) {
        super(message);
    }

    public InvalidPersonnelException(String message, Throwable cause) {
        super(message, cause);
    }
}
