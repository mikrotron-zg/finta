package hr.mikrotron.finta.importer;

import hr.mikrotron.finta.transaction.BankStatement;
import hr.mikrotron.finta.transaction.BankStatementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:importer-test.properties")
class PdfImporterTest {
  @Autowired
  private PdfImporter pdfImporter;
  @Autowired
  private BankStatementService bankStatementService;
  @Value("${pdf.import.test.filename}") // see readme for instructions!
  private String fileName;

  @Test
  void fileNotFoundTest() {
    assertFalse(pdfImporter.importFile("this_file_should_not_exist"));
  }

  @Test
  void fileAlreadyImported() {
    BankStatement bankStatement = new BankStatement();
    bankStatement.setFileName(fileName);
    bankStatement.setSequenceNumber(1);
    bankStatement.setDate(LocalDate.now());

    assertThat(bankStatementService.save(bankStatement)).isEqualTo(bankStatement);
    assertFalse(pdfImporter.importFile(fileName));
  }

  @Test
  void importFile() {
    assertTrue(pdfImporter.importFile(fileName));
  }
}