package hr.mikrotron.finta.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Different string utilities
 */
public abstract class StringUtil {

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
   * using GERMANY locale (#.##0,00 pattern)
   * @param text string to search
   * @return last decimal number in a string
   */
  public static Double extractLastNumber(String text) {
    return  extractLastNumber(text, Locale.GERMANY);
  }

  /**
   * Extract last decimal number from a string
   * using given locale
   * @param text string to search
   * @param locale locale to use for number format
   * @return last decimal number in a string
   */
  public static Double extractLastNumber(String text, Locale locale) {
    DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance(locale);
    Matcher matcher = Pattern.compile(
        String.format("([1-9]\\d{0,2}(%s?\\d{3})*|0)(\\%s\\d+)?",
            format.getDecimalFormatSymbols().getGroupingSeparator(),
            format.getDecimalFormatSymbols().getDecimalSeparator())).matcher(text);
    String result = "0";

    while (matcher.find()) {
      result = matcher.group();
    }

    try {
      return format.parse(result).doubleValue();
    } catch (ParseException parseException) {
      return 0.0;
    }
  }
}
