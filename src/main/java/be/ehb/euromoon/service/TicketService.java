package be.ehb.euromoon.service;

import be.ehb.euromoon.exception.OversellException;
import be.ehb.euromoon.exception.ValidationException;
import be.ehb.euromoon.model.journey.Journey;
import be.ehb.euromoon.model.person.Passenger;
import be.ehb.euromoon.model.ticket.ClassType;
import be.ehb.euromoon.model.ticket.Ticket;
import be.ehb.euromoon.repository.TicketRepository;

import java.util.List;

/**
 * Service for managing ticket operations.
 *
 * <p>This service handles ticket sales, validation, and retrieval.
 * It ensures that tickets cannot be oversold by checking available
 * capacity before creating new tickets. This is a critical component
 * for maintaining journey capacity constraints.
 */
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * Sells a ticket to a passenger for a specific journey and class.
     *
     * <p>This method validates that:
     * - The journey has an assigned train
     * - There are available seats in the requested class
     * - The ticket sale won't exceed capacity
     *
     * @param passenger the passenger purchasing the ticket
     * @param journey the journey to travel on
     * @param classType the class type (FIRST or SECOND)
     * @return the created ticket
     * @throws ValidationException if journey has no train or inputs are invalid
     * @throws OversellException if no seats are available
     */
    public Ticket sellTicket(Passenger passenger, Journey journey, ClassType classType)
            throws ValidationException, OversellException {
        if (passenger == null) {
            throw new ValidationException("Passenger cannot be null");
        }
        if (journey == null) {
            throw new ValidationException("Journey cannot be null");
        }
        if (classType == null) {
            throw new ValidationException("Class type cannot be null");
        }
        if (journey.getTrain() == null) {
            throw new ValidationException("Journey must have an assigned train before selling tickets");
        }

        int availableSeats = journey.getAvailableSeats(classType);
        long soldTickets = ticketRepository.countByJourneyAndClass(journey.getId(), classType);

        if (soldTickets >= availableSeats) {
            throw new OversellException(
                "No available seats in " + classType + " class for this journey. " +
                "Capacity: " + availableSeats + ", Already sold: " + soldTickets
            );
        }

        Ticket ticket = new Ticket(passenger, journey, classType);
        ticketRepository.save(ticket);

        return ticket;
    }

    /**
     * Finds all tickets for a specific journey.
     *
     * @param journey the journey to find tickets for
     * @return a list of tickets for the journey
     */
    public List<Ticket> getTicketsByJourney(Journey journey) {
        if (journey == null) {
            return List.of();
        }
        return ticketRepository.findByJourney(journey.getId());
    }

    /**
     * Returns all tickets in the system.
     *
     * @return a list of all tickets
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Gets the number of available seats for a journey and class type.
     *
     * @param journey the journey to check
     * @param classType the class type to check
     * @return the number of available seats
     */
    public long getAvailableSeats(Journey journey, ClassType classType) {
        if (journey == null || journey.getTrain() == null) {
            return 0;
        }
        int totalSeats = journey.getAvailableSeats(classType);
        long soldTickets = ticketRepository.countByJourneyAndClass(journey.getId(), classType);
        return Math.max(0, totalSeats - soldTickets);
    }
}
