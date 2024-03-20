package hr.mikrotron.finta.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Different string utilities
 */
public abstract class StringUtil {

  /**
   * Extract last integer value in a string
   * @param text
   * @return
   */
  public static Integer extractLastInteger(String text) {
    Matcher matcher = Pattern.compile("\\d+").matcher(text);
    if (!matcher.find()) return 0;
    return Integer.parseInt(matcher.group());
  }
}
