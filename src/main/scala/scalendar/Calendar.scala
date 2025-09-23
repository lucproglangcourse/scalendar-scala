package scalendar

import java.time._
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Locale

/**
 * Calendar utility class that provides functionality similar to ncal
 */
class Calendar:
  
  private val monthNames = Array(
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  )
  
  private val dayNames = Array("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa")
  
  /**
   * Display a calendar for the specified month and year
   */
  def displayMonth(year: Int, month: Int): String =
    val date = LocalDate.of(year, month, 1)
    val monthName = monthNames(month - 1)
    val header = s"    $monthName $year"
    
    val daysOfWeek = dayNames.mkString(" ")
    
    val calendar = buildMonthGrid(year, month)
    
    s"$header\n$daysOfWeek\n$calendar"
  
  /**
   * Display a calendar for the current month
   */
  def displayCurrentMonth(): String =
    val now = LocalDate.now()
    displayMonth(now.getYear, now.getMonthValue)
  
  /**
   * Display a full year calendar
   */
  def displayYear(year: Int): String =
    val header = s"                             $year\n"
    val months = (1 to 12).map(month => displayMonth(year, month)).mkString("\n\n")
    header + months
  
  /**
   * Display the current year calendar
   */
  def displayCurrentYear(): String =
    val currentYear = LocalDate.now().getYear
    displayYear(currentYear)
  
  /**
   * Build the grid of days for a given month
   */
  private def buildMonthGrid(year: Int, month: Int): String =
    val firstDay = LocalDate.of(year, month, 1)
    val lastDay = firstDay.plusMonths(1).minusDays(1)
    val daysInMonth = lastDay.getDayOfMonth
    
    // Get the day of week for the first day (0 = Sunday, 6 = Saturday)
    val firstDayOfWeek = firstDay.getDayOfWeek.getValue % 7
    
    val grid = Array.fill(6)(Array.fill(7)("  "))
    
    // Fill in the days
    var currentDay = 1
    for week <- 0 until 6; day <- 0 until 7 do
      val dayIndex = week * 7 + day
      if dayIndex >= firstDayOfWeek && currentDay <= daysInMonth then
        grid(week)(day) = f"$currentDay%2d"
        currentDay += 1
    
    // Convert grid to string, removing empty trailing weeks
    val nonEmptyWeeks = grid.takeWhile(_.exists(_ != "  "))
    nonEmptyWeeks.map(_.mkString(" ")).mkString("\n")
  
  /**
   * Get number of days in a month
   */
  def getDaysInMonth(year: Int, month: Int): Int =
    LocalDate.of(year, month, 1).plusMonths(1).minusDays(1).getDayOfMonth
  
  /**
   * Check if a year is a leap year
   */
  def isLeapYear(year: Int): Boolean =
    Year.of(year).isLeap
  
  /**
   * Get the day of week for a specific date (0 = Sunday, 6 = Saturday)
   */
  def getDayOfWeek(year: Int, month: Int, day: Int): Int =
    LocalDate.of(year, month, day).getDayOfWeek.getValue % 7