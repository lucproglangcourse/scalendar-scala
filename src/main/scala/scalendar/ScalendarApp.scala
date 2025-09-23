package scalendar

import mainargs.{main, arg, ParserForMethods, Flag}
import java.time.LocalDate

/**
 * Main application for the Scalendar command-line calendar utility
 */
object ScalendarApp:
  
  private val calendar = new Calendar
  
  @main
  def scalendar(
    @arg(name = "month", doc = "Month (1-12) to display") 
    month: Option[Int] = None,
    
    @arg(name = "year", doc = "Year to display") 
    year: Option[Int] = None,
    
    @arg(short = 'y', name = "year-view", doc = "Display the entire year")
    yearView: Flag = Flag(),
    
    @arg(short = 'h', name = "help", doc = "Show this help message")
    help: Flag = Flag()
  ): Unit =
    
    // Handle help flag
    if help.value then
      println(ParserForMethods(this).helpText())
      return
    
    // Handle year view
    if yearView.value then
      val targetYear = year.getOrElse(LocalDate.now().getYear)
      validateYear(targetYear)
      println(calendar.displayYear(targetYear))
      return
    
    // Handle specific month/year
    (month, year) match
      case (Some(m), Some(y)) =>
        validateMonth(m)
        validateYear(y)
        println(calendar.displayMonth(y, m))
        
      case (Some(m), None) =>
        validateMonth(m)
        val currentYear = LocalDate.now().getYear
        println(calendar.displayMonth(currentYear, m))
        
      case (None, Some(y)) =>
        validateYear(y)
        println(calendar.displayYear(y))
        
      case (None, None) =>
        // No arguments - show current month
        println(calendar.displayCurrentMonth())
  
  def validateMonth(month: Int): Unit =
    if month < 1 || month > 12 then
      println(s"Error: Month must be between 1 and 12, got $month")
      sys.exit(1)
  
  def validateYear(year: Int): Unit =
    if year < 1 || year > 9999 then
      println(s"Error: Year must be between 1 and 9999, got $year")
      sys.exit(1)
  
  def main(args: Array[String]): Unit =
    ParserForMethods(this).runOrExit(args.toIndexedSeq): Unit