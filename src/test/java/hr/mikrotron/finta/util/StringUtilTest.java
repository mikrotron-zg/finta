package hr.mikrotron.finta.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
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

  @Test
  void extractAllNumbers() {
    assertThat(StringUtil.extractAllNumbers("abc")).isEmpty();
    assertThat(StringUtil.extractAllNumbers("abc126,32 0,00\n 1.001,23 ")).contains(126.32, 0.0, 1001.23);
  }

  @Test
  void extractDate() {
    assertThat(StringUtil.extractFirstDate("abc")).isEmpty();
    assertThat(StringUtil.extractFirstDate("abc12.05.2024").get()).isEqualTo(LocalDate.of(2024, 5, 12));
    assertThat(StringUtil.extractFirstDate("  01.02.2024  06.04.2024").get()).isEqualTo(LocalDate.of(2024, 2, 1));
    assertThat(StringUtil.extractFirstDate("122,32\n06.04.2024").get()).isEqualTo(LocalDate.of(2024, 4, 6));
  }
}