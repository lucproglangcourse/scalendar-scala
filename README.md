# Scalendar - Scala Calendar Utility

A command-line calendar utility similar to `ncal`, written in Scala.

## Features

- Display current month calendar
- Display specific month and year
- Display entire year calendar
- Leap year support
- **Internationalization (i18n) support** - Month names, day names, and error messages in multiple languages
- Clean, formatted output similar to traditional Unix cal/ncal utilities
- **Configurable localization** via command-line options

## Usage

### Basic Usage

```bash
# Display current month
sbt run

# Display help
sbt "runMain scalendar.ScalendarApp --help"

# Display specific month in current year (e.g., March)
sbt "runMain scalendar.ScalendarApp --month 3"

# Display specific month and year (e.g., March 2024)
sbt "runMain scalendar.ScalendarApp --month 3 --year 2024"

# Display entire current year
sbt "runMain scalendar.ScalendarApp --year-view"

# Display specific year
sbt "runMain scalendar.ScalendarApp --year-view --year 2024"

# Display calendar in Spanish
sbt "runMain scalendar.ScalendarApp --month 3 --year 2024 --locale es"

# Display calendar in French  
sbt "runMain scalendar.ScalendarApp --month 3 --year 2024 --locale fr"
```

### Command Line Options

- `--help` or `-h`: Show usage information
- `--year-view` or `-y`: Display entire year calendar
- `--month <int>`: Specify month (1-12) to display
- `--year <int>`: Specify year to display
- `--locale <string>` or `-l`: Set locale for internationalization (e.g., 'es' for Spanish, 'fr' for French)

### Examples

Display March 2024 in English (default):
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

Display March 2024 in Spanish:
```
    Marzo 2024
Do Lu Ma Mi Ju Vi Sa
                1  2
 3  4  5  6  7  8  9
10 11 12 13 14 15 16
17 18 19 20 21 22 23
24 25 26 27 28 29 30
31
```

Display March 2024 in French:
```
    Mars 2024
Di Lu Ma Me Je Ve Sa
                1  2
 3  4  5  6  7  8  9
10 11 12 13 14 15 16
17 18 19 20 21 22 23
24 25 26 27 28 29 30
31
```

## Internationalization Support

Scalendar supports multiple languages through Java's ResourceBundle system. The following languages are currently supported:

- **English (en)** - Default
- **Spanish (es)** - Español
- **French (fr)** - Français

### Supported Locales

Use the `--locale` or `-l` option to specify a language:

```bash
# English (default)
sbt "run --month 3 --year 2024"

# Spanish
sbt "run --month 3 --year 2024 --locale es"

# French
sbt "run --month 3 --year 2024 --locale fr"
```

### Adding New Languages

To add support for a new language:

1. Create a new properties file in `src/main/resources/scalendar/` named `messages_XX.properties` where `XX` is the language code
2. Translate all the keys from `messages.properties`
3. The new locale will be automatically available via the `--locale` option

Example for German (`messages_de.properties`):
```properties
month.1=Januar
month.2=Februar
month.3=März
# ... etc
day.0=So
day.1=Mo
# ... etc
error.invalid.month=Ungültiger Monat: {0}. Der Monat muss zwischen 1 und 12 liegen.
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
├── main/
│   ├── scala/scalendar/
│   │   ├── Calendar.scala              # Core calendar logic
│   │   ├── ScalendarApp.scala         # Command-line interface
│   │   └── LocalizationManager.scala  # Internationalization support
│   └── resources/scalendar/
│       ├── messages.properties         # English (default)
│       ├── messages_es.properties      # Spanish
│       └── messages_fr.properties      # French
└── test/scala/scalendar/
    ├── CalendarTest.scala     # Unit tests for Calendar class
    ├── ScalendarAppTest.scala # Tests for command-line interface and i18n
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

### Calendar Factory Methods

- `Calendar.withLocale(locale)`: Create calendar with specific Java Locale
- `Calendar.withLanguage(languageTag)`: Create calendar with specific language

### LocalizationManager Class

- `getMonthName(month)`: Get localized month name
- `getDayName(dayOfWeek)`: Get localized day name  
- `getInvalidMonthError(month)`: Get localized error message for invalid month
- `getInvalidYearError(year)`: Get localized error message for invalid year
- `LocalizationManager.forLanguage(tag)`: Create manager for specific language

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

## AI Disclosure

This text contains a mix of original writing and programming with strategic use of ChatGPT via intentional prompting.
We may also make some prompts and analyses available, similar to what my colleagues have done for their recent ongoing study of ChatGPT and Systems Programming.
See also https://doi.org/10.6084/m9.figshare.22257274.
