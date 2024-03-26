package hr.mikrotron.finta.importer;

import hr.mikrotron.finta.transaction.BankStatement;
import hr.mikrotron.finta.transaction.BankStatementRepository;
import hr.mikrotron.finta.transaction.BankStatementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:importer-test.properties")
class PdfImporterTest {
  @Autowired
  private PdfImporter pdfImporter;
  @Autowired
  private BankStatementService bankStatementService;
  @Autowired
  private BankStatementRepository bankStatementRepository;
  @Value("${pdf.import.test.filename}") // see readme for instructions!
  private String fileName;
  @Value("${pdf.import.test.sequence-number}")
  private Integer sequenceNumber;
  @Value("${pdf.import.test.date}")
  private String date;
  @Value("${pdf.import.test.initial-balance}")
  private Double initialBalance;
  @Value("${pdf.import.test.final-balance}")
  private Double finalBalance;
  @Value("${pdf.import.test.debit-total}")
  private Double debitTotal;
  @Value("${pdf.import.test.credit-total}")
  private Double creditTotal;


  @Test
  void fileNotFoundTest() {
    assertThat(pdfImporter.importFile("this_file_should_not_exist")).isEmpty();
  }

  @Test
  void fileAlreadyImported() {
    BankStatement bankStatement = new BankStatement();
    bankStatement.setFileName(fileName);
    bankStatement.setSequenceNumber(1);
    bankStatement.setDate(LocalDate.now());

    assertThat(bankStatementService.save(bankStatement)).isEqualTo(bankStatement);
    assertThat(pdfImporter.importFile(fileName)).isEmpty();
  }

  @Test
  void importFile() {
    assertThat(pdfImporter.importFile(fileName)).isNotEmpty();
  }

  @Test
  void sequenceNumber() {
    assertThat(pdfImporter.importFile(fileName)
        .orElse(new BankStatement())
        .getSequenceNumber()).isEqualTo(sequenceNumber);
  }

  @Test
  void bankStatementDate() {
    assertThat(pdfImporter.importFile(fileName).orElse(new BankStatement()).getDate())
        .isEqualTo(LocalDate.parse(date));
  }

  @Test
  void bankStatementInitialBalance() {
    assertThat(pdfImporter.importFile(fileName).orElse(new BankStatement()).getInitialBalance())
        .isEqualTo(BigDecimal.valueOf(initialBalance));
  }

  @Test
  void bankStatementCreditTotal() {
    assertThat(pdfImporter.importFile(fileName).orElse(new BankStatement()).getCreditTotal())
        .isEqualTo(BigDecimal.valueOf(creditTotal));
  }

  @Test
  void bankStatementDebitTotal() {
    assertThat(pdfImporter.importFile(fileName).orElse(new BankStatement()).getDebitTotal())
        .isEqualTo(BigDecimal.valueOf(debitTotal));
  }

  @Test
  void bankStatementFinalBalance() {
    assertThat(pdfImporter.importFile(fileName).orElse(new BankStatement()).getFinalBalance())
        .isEqualTo(BigDecimal.valueOf(finalBalance));
  }
}