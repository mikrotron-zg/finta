package hr.mikrotron.finta.importer;

import hr.mikrotron.finta.transaction.BankStatement;
import hr.mikrotron.finta.transaction.BankStatementService;
import hr.mikrotron.finta.util.StringUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Imports PDF bank statement.
 */
@Component
public class PdfImporter {
  private final BankStatementService bankStatementService;
  private final BankStatement bankStatement = new BankStatement();
  private static final Logger LOGGER = LoggerFactory.getLogger(PdfImporter.class);
  @Value("${pdf.import-directory}")
  private String importDirectory;
  private PDDocument document;
  private PdfDocumentRegionConfiguration regionConfiguration;

  public PdfImporter(BankStatementService bankStatementService) {
    this.bankStatementService = bankStatementService;
  }

  public Optional<BankStatement> importFile(String fileName) {
    if (bankStatementService.findByFileName(fileName) != null) { // we already have this one
      LOGGER.debug("File {} already imported", fileName);
      return Optional.empty();
    }

    try {
      loadPdfDocument(importDirectory + fileName);
    } catch (IOException exception) {
      LOGGER.debug("File {}{} does not exist", importDirectory, fileName);
      return Optional.empty();
    }

    bankStatement.setFileName(fileName);
    regionConfiguration = new PdfDocumentRegionConfiguration();
    extractHeader();
    extractTransactions();
    return Optional.of(bankStatementService.save(bankStatement));
  }

  private void loadPdfDocument(String filePath) throws IOException {
    document = PDDocument.load(new File(filePath));
  }

  private void extractHeader() {
    bankStatement.setDate(extractStatementDate());
    bankStatement.setSequenceNumber(extractStatementSequenceNumber());
  }

  private Integer extractStatementSequenceNumber() {
    try {
      return StringUtil.extractLastInteger(
          getTextFromRegion(regionConfiguration.getRegionByName("sequence_number").getRectangle()));
    } catch (IOException exception) {
      LOGGER.debug("Got IO exception while trying to parse sequence number");
      return 0;
    }
  }

  private LocalDate extractStatementDate() {
    return LocalDate.now();
  }

  private void extractTransactions() {

  }

  private String getTextFromRegion(Rectangle2D region) throws IOException {
    return getTextFromRegion(region, 0);
  }

  private String getTextFromRegion(Rectangle2D region, Integer pageIndex) throws IOException {
    PDFTextStripperByArea textStripper = new PDFTextStripperByArea();
    textStripper.addRegion("region", region);
    PDPage docPage = document.getPage(pageIndex);
    textStripper.extractRegions(docPage);
    return textStripper.getTextForRegion("region");
  }
}
