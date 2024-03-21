package hr.mikrotron.finta.util;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

  @Test
  void extractLastInteger() {
    assertEquals(123, StringUtil.extractLastInteger("abc0123 "));
    assertEquals(55, StringUtil.extractLastInteger("123 55"));
    assertEquals(0, StringUtil.extractLastInteger("abc"));
    assertEquals(-123, StringUtil.extractLastInteger("abc-123"));
  }

  @Test
  void extractLastNumber() {
    assertEquals(12.34, StringUtil.extractLastNumber("abc12.34", Locale.UK));
    assertEquals(0.23, StringUtil.extractLastNumber("abc0,23cba"));
    assertEquals(1001.23, StringUtil.extractLastNumber("abc1.001,23"));
    assertEquals(1001.23, StringUtil.extractLastNumber("126,32 1.001,23"));
    assertEquals(0.0, StringUtil.extractLastNumber("abc"));
  }
}