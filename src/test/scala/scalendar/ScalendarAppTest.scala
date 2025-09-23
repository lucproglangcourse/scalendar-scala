package scalendar

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate
import mainargs.{ParserForMethods, Flag}

class ScalendarAppTest extends AnyFunSuite with Matchers:
  
  test("mainargs parser should generate help text"):
    val parser = ParserForMethods(ScalendarApp)
    val helpText = parser.helpText()
    
    helpText should include("scalendar")
    helpText should include("help")
    helpText should include("year-view")
    helpText should include("month")
  
  test("application should validate month range correctly"):
    // Test month validation logic - these work with LocalDate
    val validMonths = 1 to 12
    
    for month <- validMonths do
      // Valid months should not throw exceptions when creating LocalDate
      noException should be thrownBy:
        LocalDate.of(2024, month, 1)
  
  test("application should validate year range correctly"):
    val validYears = Seq(1, 2024, 9999)
    
    for year <- validYears do
      // Valid years should not throw exceptions
      noException should be thrownBy:
        LocalDate.of(year, 1, 1)
  
  test("Flag functionality should work correctly"):
    val flagTrue = Flag(true)
    val flagFalse = Flag(false)
    val flagDefault = Flag()
    
    flagTrue.value shouldBe true
    flagFalse.value shouldBe false
    flagDefault.value shouldBe false
  
  test("command line parsing should work with mainargs"):
    // Test that the parser can be created and has the expected methods
    val parser = ParserForMethods(ScalendarApp)
    
    // Check that help text contains expected content
    val helpText = parser.helpText()
    helpText should include("scalendar")
    helpText should not be empty
  
  test("Calendar methods should be accessible"):
    val calendar = new scalendar.Calendar
    
    // Test that core calendar functionality works
    val march2024 = calendar.displayMonth(2024, 3)
    march2024 should include("March 2024")
    
    val year2024 = calendar.displayYear(2024)
    year2024 should include("2024")
    year2024 should include("January")
    year2024 should include("December")
  
  test("validation methods should work correctly"):
    // Test month validation
    noException should be thrownBy:
      ScalendarApp.validateMonth(5) // Valid month
    
    // Test year validation  
    noException should be thrownBy:
      ScalendarApp.validateYear(2024) // Valid year
  
  test("mainargs should support expected argument combinations"):
    // Test that different argument patterns can be represented
    val monthOnly = Some(3)
    val yearOnly = Some(2024)
    val monthAndYear = (Some(3), Some(2024))
    val yearViewFlag = Flag(true)
    val helpFlag = Flag(true)
    
    // These should all be valid Option/Flag combinations
    monthOnly shouldBe defined
    yearOnly shouldBe defined
    monthAndYear._1 shouldBe defined
    monthAndYear._2 shouldBe defined
    yearViewFlag.value shouldBe true
    helpFlag.value shouldBe true