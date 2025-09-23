package scalendar

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate

class IntegrationTest extends AnyFunSuite with Matchers:
  
  val calendar = new Calendar()
  
  test("complete calendar workflow for known date"):
    // Test March 2024 - we know March 1, 2024 was a Friday
    val march2024 = calendar.displayMonth(2024, 3)
    
    // Verify structure
    val lines = march2024.split("\n")
    lines.length should be >= 3
    
    // Header should contain month and year
    lines(0) should include("March")
    lines(0) should include("2024")
    
    // Day names header
    lines(1) shouldBe "Su Mo Tu We Th Fr Sa"
    
    // March 1st should be in the 6th position (Friday)
    val calendarLines = lines.drop(2)
    val firstWeek = calendarLines(0)
    
    // The first week should have spaces followed by " 1" in the Friday position
    firstWeek should include(" 1")
    
    // Should contain all days of March
    for day <- 1 to 31 do
      march2024 should include(f"$day%2d")
  
  test("leap year February comparison"):
    val feb2024 = calendar.displayMonth(2024, 2) // Leap year
    val feb2023 = calendar.displayMonth(2023, 2) // Regular year
    
    // 2024 should have 29 days
    feb2024 should include("29")
    feb2024 should not include "30"
    
    // 2023 should only have 28 days
    feb2023 should not include "29"
    feb2023 should include("28")
  
  test("year view contains all months"):
    val year2024 = calendar.displayYear(2024)
    
    val expectedMonths = Array(
      "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
    )
    
    for month <- expectedMonths do
      year2024 should include(month)
    
    // Should contain year in header
    year2024 should include("2024")
  
  test("current date functionality"):
    val currentMonth = calendar.displayCurrentMonth()
    val currentYear = calendar.displayCurrentYear()
    val now = LocalDate.now()
    
    currentMonth should include(now.getYear.toString)
    currentYear should include(now.getYear.toString)
    
    // Current month should be non-empty
    currentMonth.length should be > 50
    
    // Current year should be much longer
    currentYear.length should be > 1000
  
  test("calendar formatting consistency"):
    // Test multiple months to ensure consistent formatting
    val months = (1 to 12).map(month => calendar.displayMonth(2024, month))
    
    for monthDisplay <- months do
      val lines = monthDisplay.split("\n")
      
      // Each month should have at least 3 lines (header, day names, calendar)
      lines.length should be >= 3
      
      // Day names line should always be the same
      lines(1) shouldBe "Su Mo Tu We Th Fr Sa"
      
      // Header should contain a month name and year
      lines(0) should include("2024")
  
  test("boundary conditions"):
    // Test edge cases
    
    // Minimum valid date
    val jan1 = calendar.displayMonth(1, 1)
    jan1 should include("January")
    jan1 should include("1")
    
    // Maximum month
    val dec2024 = calendar.displayMonth(2024, 12)
    dec2024 should include("December")
    dec2024 should include("2024")
    
    // Leap year edge case
    calendar.isLeapYear(2000) shouldBe true  // Divisible by 400
    calendar.isLeapYear(1900) shouldBe false // Divisible by 100 but not 400
    calendar.isLeapYear(2004) shouldBe true  // Divisible by 4
    calendar.isLeapYear(2003) shouldBe false // Not divisible by 4
  
  test("days in month calculation for all months"):
    val expectedDays = Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31) // 2024 is leap year
    
    for month <- 1 to 12 do
      calendar.getDaysInMonth(2024, month) shouldBe expectedDays(month - 1)
    
    // Non-leap year February
    calendar.getDaysInMonth(2023, 2) shouldBe 28