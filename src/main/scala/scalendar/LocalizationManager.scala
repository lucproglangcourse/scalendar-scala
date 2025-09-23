package scalendar

import java.util.{Locale, ResourceBundle}
import java.text.MessageFormat

/**
 * Handles internationalization and localization for the calendar application
 */
class LocalizationManager(locale: Locale = Locale.getDefault):
  
  private val bundle = ResourceBundle.getBundle("scalendar.messages", locale)
  
  /**
   * Get a localized month name
   */
  def getMonthName(month: Int): String =
    bundle.getString(s"month.$month")
  
  /**
   * Get a localized day name
   */
  def getDayName(dayOfWeek: Int): String =
    bundle.getString(s"day.$dayOfWeek")
  
  /**
   * Get all localized day names in order (Sunday first)
   */
  def getAllDayNames: Array[String] =
    (0 to 6).map(getDayName).toArray
  
  /**
   * Get all localized month names
   */
  def getAllMonthNames: Array[String] =
    (1 to 12).map(getMonthName).toArray
  
  /**
   * Get a localized error message for invalid month
   */
  def getInvalidMonthError(month: Int): String =
    MessageFormat.format(bundle.getString("error.invalid.month"), month.toString)
  
  /**
   * Get a localized error message for invalid year
   */
  def getInvalidYearError(year: Int): String =
    MessageFormat.format(bundle.getString("error.invalid.year"), year.toString)
  
  /**
   * Get localized help description
   */
  def getHelpDescription: String =
    bundle.getString("help.description")
  
  /**
   * Get localized usage text
   */
  def getUsageText: String =
    bundle.getString("help.usage")
  
  /**
   * Get localized examples text
   */
  def getExamplesText: String =
    bundle.getString("help.examples")
  
  /**
   * Get current locale
   */
  def getCurrentLocale: Locale = locale

object LocalizationManager:
  /**
   * Create a LocalizationManager for a specific language
   */
  def forLanguage(languageTag: String): LocalizationManager =
    val locale = Locale.forLanguageTag(languageTag)
    new LocalizationManager(locale)
  
  /**
   * Create a LocalizationManager for a specific locale
   */
  def forLocale(language: String, country: String = ""): LocalizationManager =
    val locale = if country.nonEmpty then 
      new Locale.Builder().setLanguage(language).setRegion(country).build()
    else 
      new Locale.Builder().setLanguage(language).build()
    new LocalizationManager(locale)