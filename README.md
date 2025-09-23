# Scalendar - Scala Calendar Utility

A command-line calendar utility similar to `ncal`, written in Scala.

## Features

- Display current month calendar
- Display specific month and year
- Display entire year calendar
- Leap year support
- Clean, formatted output similar to traditional Unix cal/ncal utilities

## Usage

### Basic Usage

```bash
# Display current month
sbt run

# Display help
sbt "run --help"

# Display specific month in current year (e.g., March)
sbt "run 3"

# Display specific month and year (e.g., March 2024)
sbt "run 3 2024"

# Display entire current year
sbt "run --year"

# Display specific year
sbt "run --year 2024"
```

### Command Line Options

- `--help` or `-h`: Show usage information
- `--year` or `-y`: Display entire year calendar

### Examples

Display March 2024:
```
    March 2024
Su Mo Tu We Th Fr Sa
                1  2
 3  4  5  6  7  8  9
10 11 12 13 14 15 16
17 18 19 20 21 22 23
24 25 26 27 28 29 30
31
```

## Building and Running

### Prerequisites

- Scala 3.7.3 or later (using modern significant indentation syntax)
- SBT (Scala Build Tool)

### Build

```bash
sbt compile
```

### Run

```bash
sbt run
```

### Run Tests

```bash
sbt test
```

### Create Executable

```bash
sbt stage
```

This creates an executable script in `target/universal/stage/bin/scalendar-scala`.

## Project Structure

```
src/
├── main/scala/scalendar/
│   ├── Calendar.scala      # Core calendar logic
│   └── ScalendarApp.scala  # Command-line interface
└── test/scala/scalendar/
    ├── CalendarTest.scala     # Unit tests for Calendar class
    ├── ScalendarAppTest.scala # Tests for command-line interface
    └── IntegrationTest.scala  # Integration tests
```

## Features

### Calendar Class

- `displayMonth(year, month)`: Display a specific month
- `displayCurrentMonth()`: Display current month
- `displayYear(year)`: Display entire year
- `displayCurrentYear()`: Display current year
- `getDaysInMonth(year, month)`: Get number of days in a month
- `isLeapYear(year)`: Check if a year is a leap year
- `getDayOfWeek(year, month, day)`: Get day of week for a date

### Command Line Interface

- Argument parsing and validation
- Error handling for invalid dates
- Help system
- Support for various date formats

## Testing

The project includes comprehensive tests:

- **Unit Tests**: Test individual methods and edge cases
- **Integration Tests**: Test complete workflows and real-world scenarios
- **Application Tests**: Test command-line argument parsing and validation

Run all tests with:
```bash
sbt test
```

## Development

### Adding New Features

1. Add functionality to the `Calendar` class
2. Update the `ScalendarApp` object for command-line interface changes
3. Add corresponding tests
4. Update this README

### Code Style

The project follows modern Scala 3 conventions:
- Uses significant indentation (no curly braces)
- Use camelCase for methods and variables
- Use PascalCase for classes and objects
- Include comprehensive documentation
- Write tests for all new functionality

## License

This project is open source. Feel free to use and modify as needed.