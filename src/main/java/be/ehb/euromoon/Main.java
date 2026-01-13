package be.ehb.euromoon;

import be.ehb.euromoon.model.journey.Journey;
import be.ehb.euromoon.model.person.Passenger;
import be.ehb.euromoon.model.person.personnel.*;
import be.ehb.euromoon.model.ticket.ClassType;
import be.ehb.euromoon.model.train.*;
import be.ehb.euromoon.repository.*;
import be.ehb.euromoon.service.*;
import be.ehb.euromoon.util.InputValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Main application class for the Euromoon train ticket booking system.
 *
 * <p>This class provides a command-line interface for managing passengers,
 * trains, journeys, tickets, and generating boarding lists.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static PassengerService passengerService;
    private static TrainService trainService;
    private static JourneyService journeyService;
    private static TicketService ticketService;
    private static BoardingListService boardingListService;
    private static PersonnelRepository personnelRepository;

    public static void main(String[] args) {
        initializeServices();
        initializeSampleData();

        System.out.println("=".repeat(70));
        System.out.println("   EUROMOON TRAIN TICKET BOOKING SYSTEM");
        System.out.println("=".repeat(70));
        System.out.println();

        boolean running = true;
        while (running) {
            running = showMenuAndProcess();
        }

        scanner.close();
        System.out.println("Thank you for using Euromoon Train Booking System!");
    }

    private static void initializeServices() {
        PassengerRepository passengerRepository = new PassengerRepository();
        personnelRepository = new PersonnelRepository();
        TrainRepository trainRepository = new TrainRepository();
        JourneyRepository journeyRepository = new JourneyRepository();
        TicketRepository ticketRepository = new TicketRepository();

        passengerService = new PassengerService(passengerRepository);
        trainService = new TrainService(trainRepository);
        journeyService = new JourneyService(journeyRepository);
        ticketService = new TicketService(ticketRepository);
        boardingListService = new BoardingListService(ticketService);
    }

    private static void initializeSampleData() {
        try {
            Conductor conductor1 = new Conductor("verdacht John", "Driver", "78.05.12-456.78", LocalDate.of(1978, 5, 12));
            conductor1.addCertification("Rijbewijs B1");
            personnelRepository.save(conductor1);

            Steward steward1 = new Steward("Anna", "Smith", "88.09.25-567.89", LocalDate.of(1988, 9, 25));
            steward1.addCertification("Veiligheid");
            personnelRepository.save(steward1);

            Steward steward2 = new Steward("Bob", "verdacht Johnson", "91.07.14-678.90", LocalDate.of(1991, 7, 14));
            steward2.addCertification("Toerisme");
            personnelRepository.save(steward2);

            Steward steward3 = new Steward("Clara", "Brown", "89.12.05-789.01", LocalDate.of(1989, 12, 5));
            steward3.addCertification("Veiligheid");
            personnelRepository.save(steward3);

        } catch (Exception e) {
            System.out.println("Warning: Could not initialize sample personnel data");
        }
    }

    private static boolean showMenuAndProcess() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Register passenger");
        System.out.println("2. Create journey");
        System.out.println("3. Link train to journey");
        System.out.println("4. Sell ticket to passenger");
        System.out.println("5. Print boarding list");
        System.out.println("0. Exit");
        System.out.print("\nChoose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> registerPassenger();
                case 2 -> createJourney();
                case 3 -> linkTrainToJourney();
                case 4 -> sellTicket();
                case 5 -> printBoardingList();
                case 0 -> {
                    return false;
                }
                default -> System.out.println("Invalid option. Please choose 0-5.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    private static void registerPassenger() {
        try {
            System.out.println("\n--- REGISTER PASSENGER ---");
            System.out.print("First name: ");
            String name = scanner.nextLine();

            System.out.print("Last name: ");
            String lastname = scanner.nextLine();

            System.out.print("Rijksregisternummer (YY.MM.DD-XXX.XX): ");
            String rrn = scanner.nextLine();

            if (!InputValidator.isValidRijksregisternummer(rrn)) {
                System.out.println("Warning: Rijksregisternummer format may be invalid. Expected format: YY.MM.DD-XXX.XX");
            }

            System.out.print("Birth date (yyyy-MM-dd): ");
            String birthDateStr = scanner.nextLine();
            LocalDate birthDate = InputValidator.parseDate(birthDateStr);

            Passenger passenger = passengerService.registerPassenger(name, lastname, rrn, birthDate);
            System.out.println("✓ Passenger registered successfully: " + passenger);

        } catch (Exception e) {
            System.out.println("✗ Error registering passenger: " + e.getMessage());
        }
    }

    private static void createJourney() {
        try {
            System.out.println("\n--- CREATE JOURNEY ---");
            System.out.print("Departure station: ");
            String departureStation = scanner.nextLine();

            System.out.print("Arrival station: ");
            String arrivalStation = scanner.nextLine();

            System.out.print("Departure time (yyyy-MM-ddTHH:mm, e.g., 2026-03-05T12:30): ");
            String departureTimeStr = scanner.nextLine();
            LocalDateTime departureTime = InputValidator.parseDateTime(departureTimeStr);

            Journey journey = journeyService.createJourney(departureStation, arrivalStation, departureTime);
            System.out.println("✓ Journey created successfully!");
            System.out.println("  Journey ID: " + journey.getId());
            System.out.println("  Route: " + journey);

        } catch (Exception e) {
            System.out.println("✗ Error creating journey: " + e.getMessage());
        }
    }

    private static void linkTrainToJourney() {
        try {
            System.out.println("\n--- LINK TRAIN TO JOURNEY ---");

            List<Journey> journeys = journeyService.getAllJourneys();
            if (journeys.isEmpty()) {
                System.out.println("No journeys available. Please create a journey first.");
                return;
            }

            System.out.println("\nAvailable journeys:");
            for (int i = 0; i < journeys.size(); i++) {
                Journey j = journeys.get(i);
                System.out.println((i + 1) + ". " + j + " [ID: " + j.getId() + "]");
            }

            System.out.print("\nSelect journey number: ");
            int journeyIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (journeyIndex < 0 || journeyIndex >= journeys.size()) {
                System.out.println("Invalid journey selection.");
                return;
            }
            Journey selectedJourney = journeys.get(journeyIndex);

            List<Train> trains = trainService.getAllTrains();
            if (trains.isEmpty()) {
                System.out.println("\nNo trains exist. Creating a new train...");
                System.out.print("Train ID: ");
                String trainId = scanner.nextLine();

                System.out.println("Select locomotive type:");
                System.out.println("1. Class 373 (max 12 wagons)");
                System.out.println("2. Class 374 (max 14 wagons)");
                System.out.print("Choice: ");
                int locChoice = Integer.parseInt(scanner.nextLine());

                LocomotiveType locType = locChoice == 1 ? new Class373() : new Class374();
                Train train = trainService.createTrain(trainId, locType);

                System.out.print("\nHow many first class wagons? ");
                int firstClassCount = Integer.parseInt(scanner.nextLine());
                for (int i = 1; i <= firstClassCount; i++) {
                    trainService.addWagonToTrain(trainId, i, ClassType.FIRST, 50);
                }

                System.out.print("How many second class wagons? ");
                int secondClassCount = Integer.parseInt(scanner.nextLine());
                for (int i = firstClassCount + 1; i <= firstClassCount + secondClassCount; i++) {
                    trainService.addWagonToTrain(trainId, i, ClassType.SECOND, 70);
                }

                journeyService.assignTrainToJourney(selectedJourney.getId(), train);
                System.out.println("✓ New train created and assigned to journey!");

            } else {
                System.out.println("\nAvailable trains:");
                for (int i = 0; i < trains.size(); i++) {
                    System.out.println((i + 1) + ". " + trains.get(i));
                }

                System.out.print("\nSelect train number: ");
                int trainIndex = Integer.parseInt(scanner.nextLine()) - 1;
                if (trainIndex < 0 || trainIndex >= trains.size()) {
                    System.out.println("Invalid train selection.");
                    return;
                }
                Train selectedTrain = trains.get(trainIndex);

                journeyService.assignTrainToJourney(selectedJourney.getId(), selectedTrain);
                System.out.println("✓ Train assigned to journey successfully!");
            }

            System.out.println("\nAssigning personnel to journey...");
            List<Personnel> allPersonnel = personnelRepository.findAll();
            for (Personnel p : allPersonnel) {
                journeyService.assignPersonnelToJourney(selectedJourney.getId(), p);
            }
            System.out.println("✓ Personnel assigned!");

        } catch (Exception e) {
            System.out.println("✗ Error linking train to journey: " + e.getMessage());
        }
    }

    private static void sellTicket() {
        try {
            System.out.println("\n--- SELL TICKET ---");

            List<Passenger> passengers = passengerService.getAllPassengers();
            if (passengers.isEmpty()) {
                System.out.println("No passengers registered. Please register a passenger first.");
                return;
            }

            System.out.println("\nRegistered passengers:");
            for (int i = 0; i < passengers.size(); i++) {
                System.out.println((i + 1) + ". " + passengers.get(i));
            }

            System.out.print("\nSelect passenger number: ");
            int passengerIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (passengerIndex < 0 || passengerIndex >= passengers.size()) {
                System.out.println("Invalid passenger selection.");
                return;
            }
            Passenger selectedPassenger = passengers.get(passengerIndex);

            List<Journey> journeys = journeyService.getAllJourneys();
            if (journeys.isEmpty()) {
                System.out.println("\nNo journeys available. Please create a journey first.");
                return;
            }

            System.out.println("\nAvailable journeys:");
            for (int i = 0; i < journeys.size(); i++) {
                Journey j = journeys.get(i);
                System.out.println((i + 1) + ". " + j);
                if (j.getTrain() != null) {
                    System.out.println("    First class available: " +
                        ticketService.getAvailableSeats(j, ClassType.FIRST));
                    System.out.println("    Second class available: " +
                        ticketService.getAvailableSeats(j, ClassType.SECOND));
                }
            }

            System.out.print("\nSelect journey number: ");
            int journeyIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (journeyIndex < 0 || journeyIndex >= journeys.size()) {
                System.out.println("Invalid journey selection.");
                return;
            }
            Journey selectedJourney = journeys.get(journeyIndex);

            System.out.println("\nClass type:");
            System.out.println("1. First class");
            System.out.println("2. Second class");
            System.out.print("Choice: ");
            int classChoice = Integer.parseInt(scanner.nextLine());
            ClassType classType = classChoice == 1 ? ClassType.FIRST : ClassType.SECOND;

            var ticket = ticketService.sellTicket(selectedPassenger, selectedJourney, classType);
            System.out.println("✓ Ticket sold successfully!");
            System.out.println("  " + ticket);

        } catch (Exception e) {
            System.out.println("✗ Error selling ticket: " + e.getMessage());
        }
    }

    private static void printBoardingList() {
        try {
            System.out.println("\n--- PRINT BOARDING LIST ---");

            List<Journey> journeys = journeyService.getAllJourneys();
            if (journeys.isEmpty()) {
                System.out.println("No journeys available.");
                return;
            }

            System.out.println("\nAvailable journeys:");
            for (int i = 0; i < journeys.size(); i++) {
                Journey j = journeys.get(i);
                System.out.println((i + 1) + ". " + j);
            }

            System.out.print("\nSelect journey number: ");
            int journeyIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (journeyIndex < 0 || journeyIndex >= journeys.size()) {
                System.out.println("Invalid journey selection.");
                return;
            }
            Journey selectedJourney = journeys.get(journeyIndex);

            String filename = boardingListService.generateBoardingList(selectedJourney);
            System.out.println("✓ Boarding list generated successfully!");
            System.out.println("  File: " + filename);

        } catch (Exception e) {
            System.out.println("✗ Error generating boarding list: " + e.getMessage());
        }
    }
}
