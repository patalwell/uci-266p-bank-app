package com.shakespeares.monkeys.app.dto;


import com.shakespeares.monkeys.app.model.Status;
import com.shakespeares.monkeys.app.model.TxnType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {
	private String username;
	private BigDecimal balance;
	private BigDecimal amount;
	private TxnType txnType;
	private Status status;
	private LocalDateTime createdAt;

	public TransactionDto() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
