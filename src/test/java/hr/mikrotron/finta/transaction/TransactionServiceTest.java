package hr.mikrotron.finta.transaction;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionServiceTest {
  @Autowired
  private TransactionService transactionService;

  private final Transaction firstTransaction = new Transaction();
  private final Transaction secondTransaction = new Transaction();
  private final Transaction thirdTransaction = new Transaction();

  @BeforeAll
  void setUp() {
    // Set NotNull values
    firstTransaction.setSequenceNumber(1);
    firstTransaction.setIBAN("HR444");
    firstTransaction.setName("Test1");
    firstTransaction.setDate(LocalDate.now());
    secondTransaction.setSequenceNumber(2);
    secondTransaction.setIBAN("HR555");
    secondTransaction.setName("Test2");
    secondTransaction.setDate(LocalDate.now());
    secondTransaction.setCredit(new BigDecimal(100));
    thirdTransaction.setSequenceNumber(3);
    thirdTransaction.setIBAN("HR666");
    thirdTransaction.setName("Test3");
    thirdTransaction.setDate(LocalDate.now());
    // Set pairing
    firstTransaction.setPaired(true);
    // Save to H2
    transactionService.save(firstTransaction);
    transactionService.save(secondTransaction);
    transactionService.save(thirdTransaction);
  }

  @Test
  void save() {
    assertThat(transactionService.save(firstTransaction)).isEqualTo(firstTransaction);
  }

  @Test
  void findAll() {
    assertThat(transactionService.findAll()).containsExactly(thirdTransaction, secondTransaction, firstTransaction);
  }

  @Test
  void findUnpaired() {
    assertThat(transactionService.findUnpaired(false)).containsOnly(secondTransaction, thirdTransaction);
  }

  @Test
  void testFindUnpaired() {
    assertThat(transactionService.findUnpaired()).containsOnly(secondTransaction);
  }
}