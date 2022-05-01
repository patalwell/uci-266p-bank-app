package com.shakespeares.monkeys.app.banking;

import java.sql.Timestamp;

public class Balance {

    private Timestamp transactionDate;
    private String transactionType;
    private int amount;
    private int balance;

    public Balance(Timestamp transactionDate, String transactionType, int amount, int balance) {
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                "transactionDate:" + transactionDate +
                ", transactionType:" + transactionType + '\'' +
                ", amount:" + amount +
                ", balance:" + balance +
                '}';
    }
}
