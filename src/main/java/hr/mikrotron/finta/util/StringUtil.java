package hr.mikrotron.finta.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Different string utilities
 */
public abstract class StringUtil {

  private static final Locale defaultLocale = Locale.GERMANY;

  private StringUtil() {}

  /**
   * Extract last integer value in a string
   * @param text string to search
   * @return last integer in a string
   */
  public static Integer extractLastInteger(String text) {
    Matcher matcher = Pattern.compile("-?\\d+").matcher(text);
    String result = "0";
    while (matcher.find()) {
      result = matcher.group();
    }
    return Integer.parseInt(result);
  }

  /**
   * Extract last decimal number from a string
   * using default locale
   * @param text string to search
   * @return last decimal number in a string
   */
  public static Double extractLastNumber(String text) {
    return  extractLastNumber(text, defaultLocale);
  }

  /**
   * Extract last decimal number from a string
   * using given locale
   * @param text string to search
   * @param locale locale to use for number format
   * @return last decimal number in a string
   */
  public static Double extractLastNumber(String text, Locale locale) {
    return extractAllNumbers(text, locale).stream().reduce((first, second) -> second)
        .orElse(0.0);
  }

  /**
   * Extract all decimal numbers from a string
   * using default locale
   * @param text string to search
   * @return list of doubles
   */
  public static List<Double> extractAllNumbers(String text) {
    return extractAllNumbers(text, defaultLocale);
  }

  /**
   * Extract all decimal numbers from a string
   * using given locale
   * @param text string to search
   * @param locale to use for number format
   * @return list of doubles
   */
  public static List<Double> extractAllNumbers(String text, Locale locale) {
    List<Double> results = new ArrayList<>();
    DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance(locale);
    Matcher matcher = Pattern.compile(
        String.format("([1-9]\\d{0,2}(%s?\\d{3})*|0)(\\%s\\d+)?",
            format.getDecimalFormatSymbols().getGroupingSeparator(),
            format.getDecimalFormatSymbols().getDecimalSeparator())).matcher(text);
    while (matcher.find()) {
      try {
        results.add(format.parse(matcher.group()).doubleValue());
      } catch (ParseException parseException) {
        // TODO log exception
      }
    }
    return results;
  }

  /**
   * Extract date from a string
   * @param text string to search
   * @return date
   */
  public static Optional<LocalDate> extractFirstDate(String text) {
    Matcher matcher = Pattern.compile("\\d{2}.\\d{2}.\\d{4}").matcher(text);
    if (matcher.find()) {
      return Optional.of(LocalDate.parse(matcher.group(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
    return Optional.empty();
  }
}

