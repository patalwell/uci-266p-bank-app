package com.shakespeares.monkeys.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;



@Entity
@Table(name = "transactions", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class TransactionInfo {

  @Id
  @GeneratedValue
  private UUID id;
  private String userName;
  private BigDecimal amount;
  private BigDecimal balance;
  private TxnType txnType;
  private Status status;
  private LocalDateTime createdAt;

  public TransactionInfo(String userName, BigDecimal amount, BigDecimal balance, TxnType txnType, Status status, LocalDateTime createdAt) {
    this.userName = userName;
    this.amount = amount;
    this.balance = balance;
    this.txnType = txnType;
    this.status = status;
    this.createdAt = createdAt;
  }

  public TransactionInfo() {

  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public TxnType getTxnType() {
    return txnType;
  }

  public void setTxnType(TxnType txnType) {
    this.txnType = txnType;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
