package be.ehb.euromoon.service;

import be.ehb.euromoon.model.journey.Journey;
import be.ehb.euromoon.model.person.Passenger;
import be.ehb.euromoon.model.person.personnel.Personnel;
import be.ehb.euromoon.model.ticket.ClassType;
import be.ehb.euromoon.model.ticket.Ticket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for generating boarding lists for journeys.
 *
 * <p>This service creates text files containing passenger and personnel
 * information for specific journeys. The files are named according to
 * the format: Station1_Station2_DateTime.txt
 */
public class BoardingListService {
    private final TicketService ticketService;

    public BoardingListService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Generates a boarding list file for a journey.
     *
     * <p>The file contains:
     * - Journey information (stations, departure time)
     * - Passengers grouped by class type
     * - Assigned personnel
     *
     * @param journey the journey to generate the boarding list for
     * @return the filename of the generated file
     * @throws IOException if file creation fails
     */
    public String generateBoardingList(Journey journey) throws IOException {
        String filename = formatFilename(
            journey.getDepartureStation(),
            journey.getArrivalStation(),
            journey.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
        );

        List<Ticket> tickets = ticketService.getTicketsByJourney(journey);

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writeHeader(writer, journey);
            writePassengers(writer, tickets);
            writePersonnel(writer, journey);
        }

        return filename;
    }

    private void writeHeader(PrintWriter writer, Journey journey) {
        writer.println("EUROMOON BOARDING LIST");
        writer.println("Journey: " + journey.getDepartureStation() + " -> " + journey.getArrivalStation());
        writer.println("Departure: " + journey.getDepartureTime());
        writer.println("Train: " + (journey.getTrain() != null ? journey.getTrain().getTrainId() : "Not assigned"));
        writer.println("=".repeat(70));
        writer.println();
    }

    private void writePassengers(PrintWriter writer, List<Ticket> tickets) {
        Map<ClassType, List<Ticket>> byClass = tickets.stream()
            .collect(Collectors.groupingBy(Ticket::getClassType));

        for (ClassType classType : ClassType.values()) {
            List<Ticket> classTickets = byClass.get(classType);
            writer.println(classType + " CLASS:");

            if (classTickets == null || classTickets.isEmpty()) {
                writer.println("  No passengers");
            } else {
                for (int i = 0; i < classTickets.size(); i++) {
                    Ticket ticket = classTickets.get(i);
                    Passenger passenger = ticket.getPassenger();
                    writer.printf("  %d. %s %s (RRN: %s, DOB: %s)%n",
                        i + 1,
                        passenger.getName(),
                        passenger.getLastname(),
                        passenger.getRijksregisternummer(),
                        passenger.getGeboortedatum()
                    );
                }
            }
            writer.println();
        }
    }

    private void writePersonnel(PrintWriter writer, Journey journey) {
        writer.println("ASSIGNED PERSONNEL:");
        List<Personnel> personnel = journey.getAssignedPersonnel();

        if (personnel.isEmpty()) {
            writer.println("  No personnel assigned");
        } else {
            for (Personnel person : personnel) {
                writer.printf("  %s: %s %s (RRN: %s)%n",
                    person.getClass().getSimpleName(),
                    person.getName(),
                    person.getLastname(),
                    person.getRijksregisternummer()
                );
            }
        }
        writer.println();
        writer.println("=".repeat(70));
        writer.println("Total passengers: " + personnel.size());
    }

    private String formatFilename(String from, String to, String dateTime) {
        String fromClean = from.replaceAll("\\s+", "_");
        String toClean = to.replaceAll("\\s+", "_");
        String dateClean = dateTime.replaceAll("[:\\s]", "");
        return fromClean + "_" + toClean + "_" + dateClean + ".txt";
    }
}
