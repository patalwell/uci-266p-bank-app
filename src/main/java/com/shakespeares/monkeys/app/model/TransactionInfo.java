package com.shakespeares.monkeys.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TransactionInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private BigDecimal amount;
  private BigDecimal balance = new BigDecimal("0.00");
  private TxnType txnType;
  private boolean active = true;
  private Status status;
  private LocalDateTime createdAt = LocalDateTime.now();
}
