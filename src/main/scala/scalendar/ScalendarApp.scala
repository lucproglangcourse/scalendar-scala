package scalendar

import java.time.LocalDate

/**
 * Main application for the Scalendar command-line calendar utility
 */
object ScalendarApp:
  
  private val calendar = new Calendar()
  private val usage = 
    """Usage: scalendar [options] [month] [year]
      |Options:
      |  -h, --help     Show this help message
      |  -y, --year     Display the entire year
      |  
      |Examples:
      |  scalendar              Display current month
      |  scalendar 3 2024       Display March 2024
      |  scalendar -y           Display current year
      |  scalendar -y 2024      Display year 2024
      |""".stripMargin
  
  def run(arguments: Array[String]): Unit =
    val argList = if arguments == null then List.empty else arguments.toList
    argList match
      case Nil =>
        // No arguments - show current month
        println(calendar.displayCurrentMonth())
        
      case List("-h") | List("--help") =>
        // Help option
        println(usage)
        
      case List("-y") | List("--year") =>
        // Current year
        println(calendar.displayCurrentYear())
        
      case List("-y", year) =>
        // Specific year with -y
        try
          val yearInt = year.toInt
          if yearInt < 1 || yearInt > 9999 then
            println(s"Error: Year must be between 1 and 9999")
            sys.exit(1)
          println(calendar.displayYear(yearInt))
        catch
          case _: NumberFormatException =>
            println(s"Error: Invalid year '$year'")
            sys.exit(1)
        
      case List("--year", year) =>
        // Specific year with --year
        try
          val yearInt = year.toInt
          if yearInt < 1 || yearInt > 9999 then
            println(s"Error: Year must be between 1 and 9999")
            sys.exit(1)
          println(calendar.displayYear(yearInt))
        catch
          case _: NumberFormatException =>
            println(s"Error: Invalid year '$year'")
            sys.exit(1)
        
      case List(month) =>
        // Month in current year
        try
          val monthInt = month.toInt
          val currentYear = LocalDate.now().getYear
          if monthInt < 1 || monthInt > 12 then
            println(s"Error: Month must be between 1 and 12")
            sys.exit(1)
          println(calendar.displayMonth(currentYear, monthInt))
        catch
          case _: NumberFormatException =>
            println(s"Error: Invalid month '$month'")
            sys.exit(1)
        
      case List(month, year) =>
        // Specific month and year
        try
          val monthInt = month.toInt
          val yearInt = year.toInt
          
          if monthInt < 1 || monthInt > 12 then
            println(s"Error: Month must be between 1 and 12")
            sys.exit(1)
          
          if yearInt < 1 || yearInt > 9999 then
            println(s"Error: Year must be between 1 and 9999")
            sys.exit(1)
          
          println(calendar.displayMonth(yearInt, monthInt))
        catch
          case _: NumberFormatException =>
            println(s"Error: Invalid month '$month' or year '$year'")
            sys.exit(1)
        
      case _ =>
        // Invalid arguments
        println("Error: Invalid arguments")
        println(usage)
        sys.exit(1)
  
  def main(args: Array[String]): Unit =
    run(args)