# Euromoon Train Ticket Booking System

A command-line Java application for managing train ticket bookings, journeys, and passenger information for the Euromoon railway company operating international routes across Europe.

## Project Description

This application allows users to:
- Register passengers
- Create train journeys between European capitals
- Manage trains with different locomotive types
- Sell tickets with automatic capacity validation
- Generate boarding lists for journeys

## Features

- **Passenger Management**: Register passengers with validated national registry numbers
- **Train System**: Flexible locomotive types (Class 373, Class 374) with configurable wagons
- **Journey Planning**: Schedule journeys with automatic personnel validation
- **Ticket Sales**: Prevents overselling with real-time capacity checking
- **Boarding Lists**: Automatic generation of formatted boarding list files

## Technical Details

- **Language**: Java 17
- **Build Tool**: Maven
- **Architecture**: Service layer pattern with repository pattern for data management
- **Design Patterns**:
  - Strategy Pattern for locomotive types (extensible for new models)
  - Inheritance for person hierarchy (Passenger, Personnel, Conductor, Steward, etc.)
  - Repository pattern for in-memory data storage

## Project Structure

```
be.ehb.euromoon/
├── model/           # Domain entities (Person, Train, Journey, Ticket)
├── service/         # Business logic layer
├── repository/      # Data access layer (in-memory storage)
├── exception/       # Custom exception classes
├── util/            # Utility classes for validation
└── Main.java        # Application entry point with menu system
```

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Compilation
```bash
mvn clean compile
```

### Running the Application
```bash
mvn exec:java -Dexec.mainClass="be.ehb.euromoon.Main"
```

### Generating Javadoc
```bash
mvn javadoc:javadoc
```

## Menu Options

1. **Register passenger** - Add new passengers to the system
2. **Create journey** - Schedule new journeys between stations
3. **Link train to journey** - Assign trains and personnel to journeys
4. **Sell ticket to passenger** - Purchase tickets with automatic capacity validation
5. **Print boarding list** - Generate formatted boarding list files
0. **Exit** - Close the application

## Key Requirements Met

- ✅ Person hierarchy with extensible personnel types
- ✅ Strategy pattern for locomotive types (easily add new types)
- ✅ Journey validation (minimum 1 conductor, 3 stewards)
- ✅ Oversell prevention (validates capacity before ticket sale)
- ✅ Boarding list generation (Station1_Station2_DateTime.txt format)
- ✅ Proper package structure and naming conventions
- ✅ Comprehensive Javadoc documentation
- ✅ Exception handling and input validation
- ✅ In-memory data persistence

## AI Tools Used

This project was developed with assistance from the following AI tools:

- **Claude Code (Claude Sonnet 4.5)** - Used for:
  - Project architecture design and planning
  - Implementation of all Java classes
  - Code structure and design pattern application
  - Javadoc documentation generation
  - Testing and debugging assistance

The AI tool helped ensure:
- Proper implementation of design patterns (Strategy, Repository)
- Clean code structure following Java best practices
- Comprehensive error handling and validation
- Well-documented code with Javadoc comments

## Author

Ilyas Assakali
EHB - Programming Advanced Course

## License

This project is developed as an academic assignment for EHB.
