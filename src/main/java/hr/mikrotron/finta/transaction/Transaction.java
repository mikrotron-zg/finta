package hr.mikrotron.finta.transaction;

import hr.mikrotron.finta.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("unused")
@Entity
public class Transaction extends AbstractEntity {

  @NotNull
  private Integer sequenceNumber;
  @NotBlank
  @Column(length = 34) // by definition IBAN is max 34 characters long
  private String IBAN;
  @NotBlank
  @Column(length = 50)
  private String name;
  @Column(length = 50)
  private String address;
  @Column(length = 50)
  private String city;
  @Column(length = 4)
  private String referenceModel;
  @Column(length = 30)
  private String referenceNumber;
  @Column(length = 50)
  private String description;
  @NotNull
  private LocalDate date;
  private BigDecimal debit;
  private BigDecimal credit;
  @NotNull
  private Boolean paired = false;

  @ManyToOne
  private BankStatement bankStatement;

  public Integer getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(Integer sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public String getIBAN() {
    return IBAN;
  }

  public void setIBAN(String IBAN) {
    this.IBAN = IBAN;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getReferenceModel() {
    return referenceModel;
  }

  public void setReferenceModel(String referenceModel) {
    this.referenceModel = referenceModel;
  }

  public String getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public Boolean getPaired() {
    return paired;
  }

  public void setPaired(Boolean paired) {
    this.paired = paired;
  }

  public BankStatement getBankStatement() {
    return bankStatement;
  }

  public void setBankStatement(BankStatement bankStatement) {
    this.bankStatement = bankStatement;
  }
}
