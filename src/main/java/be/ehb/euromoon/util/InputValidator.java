package be.ehb.euromoon.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for validating user inputs.
 *
 * <p>This class provides static methods for validating various types
 * of input data such as rijksregisternummer, dates, and strings.
 */
public class InputValidator {

    /**
     * Validates the format of a rijksregisternummer.
     *
     * <p>Expected format: YY.MM.DD-XXX.XX (e.g., 90.01.15-123.45)
     *
     * @param rijksregisternummer the rijksregisternummer to validate
     * @return true if the format is valid, false otherwise
     */
    public static boolean isValidRijksregisternummer(String rijksregisternummer) {
        if (rijksregisternummer == null) {
            return false;
        }
        return rijksregisternummer.matches("\\d{2}\\.\\d{2}\\.\\d{2}-\\d{3}\\.\\d{2}");
    }

    /**
     * Validates that a string is not null or empty.
     *
     * @param value the string to validate
     * @return true if the string is valid, false otherwise
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Parses a date string in the format yyyy-MM-dd.
     *
     * @param dateString the date string to parse
     * @return the parsed LocalDate
     * @throws IllegalArgumentException if the date format is invalid
     */
    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected: yyyy-MM-dd (e.g., 1990-01-15)");
        }
    }

    /**
     * Parses a datetime string in the format yyyy-MM-ddTHH:mm.
     *
     * @param datetimeString the datetime string to parse
     * @return the parsed LocalDateTime
     * @throws IllegalArgumentException if the datetime format is invalid
     */
    public static LocalDateTime parseDateTime(String datetimeString) {
        try {
            return LocalDateTime.parse(datetimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid datetime format. Expected: yyyy-MM-ddTHH:mm (e.g., 2026-03-05T12:30)");
        }
    }

    /**
     * Validates that a number is positive.
     *
     * @param value the number to validate
     * @return true if the number is positive, false otherwise
     */
    public static boolean isPositive(int value) {
        return value > 0;
    }
}
