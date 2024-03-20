package hr.mikrotron.finta.util;

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
    if (!matcher.find()) return 0;
    return Integer.parseInt(matcher.group());
  }

  /**
   * Extract last decimal number from a string
   * @param text string to search
   * @return last decimal number in a string
   */
  public static Double extractLastNumber(String text) {
    Matcher matcher = Pattern.compile("-?\\d+(\\.\\d+)?").matcher(text);
    if (!matcher.find()) return 0.0;
    return Double.parseDouble(matcher.group());
  }
}
