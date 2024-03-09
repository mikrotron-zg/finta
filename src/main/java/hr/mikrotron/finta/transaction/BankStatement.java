package hr.mikrotron.finta.transaction;

import hr.mikrotron.finta.model.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
public class BankStatement extends AbstractEntity {

  @NotBlank
  private String fileName;
  @NotNull
  private Integer sequenceNumber;
  @NotNull
  private LocalDate date;
  @NotNull
  private BigDecimal initialBalance;
  @NotNull
  private BigDecimal finalBalance;
  @NotNull
  private BigDecimal debitTotal;
  @NotNull
  private BigDecimal creditTotal;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "bankStatement")
  private Set<Transaction> transactions = new HashSet<>();

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  public Integer getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(Integer sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public BigDecimal getInitialBalance() {
    return initialBalance;
  }

  public void setInitialBalance(BigDecimal initialBalance) {
    this.initialBalance = initialBalance;
  }

  public BigDecimal getFinalBalance() {
    return finalBalance;
  }

  public void setFinalBalance(BigDecimal finalBalance) {
    this.finalBalance = finalBalance;
  }

  public BigDecimal getDebitTotal() {
    return debitTotal;
  }

  public void setDebitTotal(BigDecimal debitTotal) {
    this.debitTotal = debitTotal;
  }

  public BigDecimal getCreditTotal() {
    return creditTotal;
  }

  public void setCreditTotal(BigDecimal creditTotal) {
    this.creditTotal = creditTotal;
  }

  public Set<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(Set<Transaction> transactions) {
    this.transactions = transactions;
  }
}
