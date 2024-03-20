package hr.mikrotron.finta.importer;

import java.util.ArrayList;
import java.util.List;
/**
 * PDF specific regions configuration
 */
class PdfDocumentRegionConfiguration {
  private final List<PdfDocumentRegion> regions = new ArrayList<>();

  PdfDocumentRegionConfiguration() {
    generateDefaultConfiguration();
  }

  private void generateDefaultConfiguration() {
    regions.add(new PdfDocumentRegion(0, 40, 200, 20, "sequence_number"));
    regions.add(new PdfDocumentRegion(355, 40, 100, 20, "date"));
    regions.add(new PdfDocumentRegion(740, 40, 70, 70, "balance"));
    regions.add(new PdfDocumentRegion(0, 170, 50, 500, "column1"));
    regions.add(new PdfDocumentRegion(50, 170, 180, 500, "column2"));
    regions.add(new PdfDocumentRegion(240, 170, 100, 500, "column3"));
    regions.add(new PdfDocumentRegion(340, 170, 200, 500, "column4"));
    regions.add(new PdfDocumentRegion(550, 170, 100, 500, "column5"));
    regions.add(new PdfDocumentRegion(650, 170, 70, 500, "column6"));
    regions.add(new PdfDocumentRegion(720, 170, 100, 500, "column7"));
  }

  List<PdfDocumentRegion> getRegions() {
    return regions;
  }

  PdfDocumentRegion getRegionByName(String name) {
    return regions.stream().filter(region -> region.getName().contains(name)).findFirst().orElse(null);
  }
}
