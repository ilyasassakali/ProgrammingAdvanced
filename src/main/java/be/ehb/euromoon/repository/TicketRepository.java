package be.ehb.euromoon.repository;

import be.ehb.euromoon.model.ticket.Ticket;
import be.ehb.euromoon.model.ticket.ClassType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Repository for managing tickets in memory.
 *
 * <p>This repository stores tickets using their UUID as a unique identifier.
 * It provides methods for saving, finding, and managing ticket records,
 * including journey-specific and class-specific queries.
 */
public class TicketRepository {
    private final Map<UUID, Ticket> tickets = new HashMap<>();

    /**
     * Saves a ticket to the repository.
     *
     * @param ticket the ticket to save
     */
    public void save(Ticket ticket) {
        if (ticket != null && ticket.getId() != null) {
            tickets.put(ticket.getId(), ticket);
        }
    }

    /**
     * Finds a ticket by its ID.
     *
     * @param id the ticket ID to search for
     * @return an Optional containing the ticket if found, empty otherwise
     */
    public Optional<Ticket> findById(UUID id) {
        return Optional.ofNullable(tickets.get(id));
    }

    /**
     * Returns all tickets in the repository.
     *
     * @return a list of all tickets
     */
    public List<Ticket> findAll() {
        return new ArrayList<>(tickets.values());
    }

    /**
     * Finds all tickets for a specific journey.
     *
     * @param journeyId the journey ID to search for
     * @return a list of tickets for the specified journey
     */
    public List<Ticket> findByJourney(UUID journeyId) {
        return tickets.values().stream()
            .filter(t -> t.getJourney().getId().equals(journeyId))
            .collect(Collectors.toList());
    }

    /**
     * Counts tickets for a specific journey and class type.
     *
     * @param journeyId the journey ID
     * @param classType the class type
     * @return the number of tickets sold
     */
    public long countByJourneyAndClass(UUID journeyId, ClassType classType) {
        return tickets.values().stream()
            .filter(t -> t.getJourney().getId().equals(journeyId))
            .filter(t -> t.getClassType() == classType)
            .count();
    }
}
