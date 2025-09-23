package scalendar

import mainargs.{main, arg, ParserForMethods, Flag}
import java.time.LocalDate
import java.util.Locale

/**
 * Main application for the Scalendar command-line calendar utility
 */
object ScalendarApp:
  
  private val l10n = new LocalizationManager()
  private val calendar = new Calendar(l10n)
  
  @main
  def scalendar(
    @arg(name = "month", doc = "Month (1-12) to display") 
    month: Option[Int] = None,
    
    @arg(name = "year", doc = "Year to display") 
    year: Option[Int] = None,
    
    @arg(short = 'y', name = "year-view", doc = "Display the entire year")
    yearView: Flag = Flag(),
    
    @arg(short = 'l', name = "locale", doc = "Set locale (e.g., 'es' for Spanish, 'fr' for French)")
    locale: Option[String] = None,
    
    @arg(short = 'h', name = "help", doc = "Show this help message")
    help: Flag = Flag()
  ): Unit =
    
    // Set up calendar with specified locale if provided
    val activeCalendar = locale match
      case Some(lang) => Calendar.withLanguage(lang)
      case None => calendar
    
    // Handle help flag
    if help.value then
      println(ParserForMethods(this).helpText())
      return
    
    // Handle year view
    if yearView.value then
      val targetYear = year.getOrElse(LocalDate.now().getYear)
      validateYear(targetYear, activeCalendar.getLocalizationManager)
      println(activeCalendar.displayYear(targetYear))
      return
    
    // Handle specific month/year
    (month, year) match
      case (Some(m), Some(y)) =>
        validateMonth(m, activeCalendar.getLocalizationManager)
        validateYear(y, activeCalendar.getLocalizationManager)
        println(activeCalendar.displayMonth(y, m))
        
      case (Some(m), None) =>
        validateMonth(m, activeCalendar.getLocalizationManager)
        val currentYear = LocalDate.now().getYear
        println(activeCalendar.displayMonth(currentYear, m))
        
      case (None, Some(y)) =>
        validateYear(y, activeCalendar.getLocalizationManager)
        println(activeCalendar.displayYear(y))
        
      case (None, None) =>
        // No arguments - show current month
        println(activeCalendar.displayCurrentMonth())
  
  def validateMonth(month: Int, l10n: LocalizationManager): Unit =
    if month < 1 || month > 12 then
      println(l10n.getInvalidMonthError(month))
      sys.exit(1)
  
  def validateYear(year: Int, l10n: LocalizationManager): Unit =
    if year < 1900 || year > 3000 then
      println(l10n.getInvalidYearError(year))
      sys.exit(1)
  
  def main(args: Array[String]): Unit =
    ParserForMethods(this).runOrExit(args.toIndexedSeq): Unit