package hr.mikrotron.finta.transaction;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankStatementServiceTest {
  @Autowired
  private BankStatementService bankStatementService;
  @Autowired
  private TransactionService transactionService;
  private final List<BankStatement> bankStatements = new ArrayList<>();

  @BeforeAll
  void setUp() {
    for (int i = 0; i < 3; i++) {
      bankStatements.add(new BankStatement());
      bankStatements.get(i).setFileName("file");
      bankStatements.get(i).setSequenceNumber(i+ 1);
      bankStatements.get(i).setDate(LocalDate.now().plusDays(i));
      bankStatementService.save(bankStatements.get(i));
    }
  }

  @Test
  void save() {
    Transaction transaction = new Transaction();
    transaction.setSequenceNumber(1);
    transaction.setIBAN("HR444");
    transaction.setName("Test1");
    transaction.setDate(LocalDate.now());
    Set<Transaction> transactions = new HashSet<>();
    transactions.add(transaction);
    bankStatements.get(0).setTransactions(transactions);
    assertThat(bankStatementService.save(bankStatements.get(0))).isEqualTo(bankStatements.get(0));
    assertThat(transactionService.findAll()).contains(transaction);
  }

  @Test
  void findAll() {
    assertThat(bankStatementService.findAll()).containsExactly(
        bankStatements.get(2), bankStatements.get(1), bankStatements.get(0));
  }

  @Test
  void findByFileName() {
    assertThat(bankStatementService.findByFileName("test")).isNull();
    assertThat(bankStatementService.findByFileName("file")).isNotNull();
  }
}