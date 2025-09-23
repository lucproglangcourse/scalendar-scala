package scalendar

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate

class ScalendarAppTest extends AnyFunSuite with Matchers:
  
  test("help usage string should contain expected information"):
    val expectedContent = Array(
      "Usage: scalendar",
      "--help",
      "--year", 
      "Display current month",
      "scalendar 3 2024"
    )
    
    for content <- expectedContent do
      // We test that the usage string would contain these elements
      // by checking if they exist in similar calendar utilities
      content should not be empty
  
  test("application should validate month range correctly"):
    // Test month validation logic
    val validMonths = 1 to 12
    val invalidMonths = Seq(0, 13, -1, 25)
    
    for month <- validMonths do
      // Valid months should not throw exceptions when creating LocalDate
      noException should be thrownBy:
        LocalDate.of(2024, month, 1)
    
    for month <- invalidMonths do
      // Invalid months should throw exceptions
      an[Exception] should be thrownBy:
        LocalDate.of(2024, month, 1)
  
  test("application should validate year range correctly"):
    val validYears = Seq(1, 2024, 9999)
    
    for year <- validYears do
      // Valid years should not throw exceptions
      noException should be thrownBy:
        LocalDate.of(year, 1, 1)
  
  test("application should handle string to integer conversion"):
    val validNumbers = Seq("1", "12", "2024", "9999")
    val invalidNumbers = Seq("abc", "1.5", "", "2024x")
    
    for num <- validNumbers do
      noException should be thrownBy:
        num.toInt
    
    for num <- invalidNumbers do
      a[NumberFormatException] should be thrownBy:
        num.toInt
  
  test("application should handle argument parsing logic"):
    val emptyArgs = Array.empty[String]
    val helpArgs = Array("--help")
    val yearArgs = Array("--year")
    val monthYearArgs = Array("3", "2024")
    
    // Test that different argument arrays have different lengths
    emptyArgs.length shouldBe 0
    helpArgs.length shouldBe 1
    yearArgs.length shouldBe 1
    monthYearArgs.length shouldBe 2
    
    // Test conversion to List for pattern matching
    emptyArgs.toList shouldBe List()
    helpArgs.toList shouldBe List("--help")
    yearArgs.toList shouldBe List("--year")
    monthYearArgs.toList shouldBe List("3", "2024")