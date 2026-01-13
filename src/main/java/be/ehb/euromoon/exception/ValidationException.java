package be.ehb.euromoon.exception;

/**
 * Exception thrown when input validation fails.
 *
 * <p>This exception is used for general validation errors such as invalid
 * dates, malformed rijksregisternummer, empty required fields, or other
 * data validation failures.
 */
public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
