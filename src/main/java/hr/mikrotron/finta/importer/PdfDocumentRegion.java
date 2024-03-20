package hr.mikrotron.finta.importer;

import java.awt.geom.*;

/**
 * Simple data class to simplify PDF document regions handling
 */
class PdfDocumentRegion {
  private Integer x;
  private Integer y;
  private Integer width;
  private Integer height;
  private String name;

  public PdfDocumentRegion() {
    this(0, 0, 0, 0, "");
  }

  public PdfDocumentRegion(Integer x, Integer y, Integer width, Integer height, String name) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.name = name;
  }

  public Rectangle2D getRectangle(String name) {
    return this.getRectangle();
  }

  public Rectangle2D getRectangle() {
    return new Rectangle2D.Float(x, y, width, height);
  }

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
