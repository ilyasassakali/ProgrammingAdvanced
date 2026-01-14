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

## Product Backlog

### Sprint 1: Foundation & Core Models (Week 1)

| ID | User Story | Priority | Story Points | Status |
|----|------------|----------|--------------|--------|
| US-01 | As a developer, I want to set up the Maven project structure so that I can organize code properly | Must Have | 2 | ✅ Done |
| US-02 | As a system, I need a Person class hierarchy so that passengers and personnel can be managed | Must Have | 5 | ✅ Done |
| US-03 | As a developer, I want extensible personnel types so that new roles can be added in the future | Must Have | 3 | ✅ Done |
| US-04 | As a system, I need a locomotive type system that follows Strategy pattern for extensibility | Must Have | 5 | ✅ Done |
| US-05 | As a system, I need Train and Wagon classes to represent complete trains | Must Have | 3 | ✅ Done |

### Sprint 2: Business Logic & Services (Week 2)

| ID | User Story | Priority | Story Points | Status |
|----|------------|----------|--------------|--------|
| US-06 | As a user, I want to register passengers with validation so that data is correct | Must Have | 5 | ✅ Done |
| US-07 | As a system, I need to validate rijksregisternummer format to ensure data quality | Should Have | 2 | ✅ Done |
| US-08 | As a user, I want to create train journeys between stations | Must Have | 3 | ✅ Done |
| US-09 | As a system, I need to assign trains to journeys with personnel validation | Must Have | 5 | ✅ Done |
| US-10 | As a system, I must ensure each journey has at least 1 conductor and 3 stewards | Must Have | 3 | ✅ Done |

### Sprint 3: Ticket Sales & Validation (Week 3)

| ID | User Story | Priority | Story Points | Status |
|----|------------|----------|--------------|--------|
| US-11 | As a user, I want to sell tickets to passengers for specific journeys | Must Have | 5 | ✅ Done |
| US-12 | As a system, I must prevent overselling tickets beyond train capacity | Must Have | 8 | ✅ Done |
| US-13 | As a system, I need to track tickets by class type (first/second) | Must Have | 3 | ✅ Done |
| US-14 | As a user, I want to see available seats before purchasing tickets | Should Have | 2 | ✅ Done |
| US-15 | As a system, I need custom exceptions for validation and error handling | Must Have | 3 | ✅ Done |

### Sprint 4: Reporting & User Interface (Week 4)

| ID | User Story | Priority | Story Points | Status |
|----|------------|----------|--------------|--------|
| US-16 | As a user, I want to generate boarding lists as text files | Must Have | 5 | ✅ Done |
| US-17 | As a system, I need to format boarding list filenames as Station1_Station2_DateTime.txt | Must Have | 2 | ✅ Done |
| US-18 | As a user, I want a command-line menu to access all features | Must Have | 5 | ✅ Done |
| US-19 | As a user, I want clear error messages when operations fail | Should Have | 3 | ✅ Done |
| US-20 | As a developer, I want comprehensive Javadoc on all classes | Must Have | 5 | ✅ Done |

### Technical Debt & Quality

| ID | Task | Priority | Story Points | Status |
|----|------|----------|--------------|--------|
| TD-01 | Add wagon limit validation based on locomotive type | Must Have | 2 | ✅ Done |
| TD-02 | Implement Repository pattern for data management | Must Have | 5 | ✅ Done |
| TD-03 | Create comprehensive exception handling system | Must Have | 3 | ✅ Done |
| TD-04 | Add input validation utility class | Should Have | 2 | ✅ Done |
| TD-05 | Initialize git repository with .gitignore | Must Have | 1 | ✅ Done |

### Definition of Done

A user story is considered "Done" when:
- ✅ Code is written and follows Java naming conventions
- ✅ Javadoc is added to all public classes and methods
- ✅ Code compiles without errors
- ✅ Manual testing is completed successfully
- ✅ Code is committed to git with meaningful commit message
- ✅ No overselling is possible (for ticket-related stories)
- ✅ Proper exception handling is implemented

### Prioritization (MoSCoW Method)

- **Must Have**: Core functionality required for MVP (Minimum Viable Product)
- **Should Have**: Important but not critical for initial release
- **Could Have**: Nice to have features (not implemented in this version)
- **Won't Have**: Explicitly excluded from this release

### Story Points Estimation

- **1-2 points**: Simple task, < 2 hours
- **3 points**: Moderate complexity, 2-4 hours
- **5 points**: Complex task, 4-8 hours
- **8 points**: Very complex, requires careful design, 8+ hours

### Total Sprint Summary

| Sprint | Total Story Points | Completed | Status |
|--------|-------------------|-----------|--------|
| Sprint 1 | 18 | 18 | ✅ Complete |
| Sprint 2 | 18 | 18 | ✅ Complete |
| Sprint 3 | 21 | 21 | ✅ Complete |
| Sprint 4 | 20 | 20 | ✅ Complete |
| **Total** | **77** | **77** | ✅ **100% Complete** |

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
