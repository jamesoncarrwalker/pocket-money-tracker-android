package com.example.pocketmoneytracker.models;

import com.example.pocketmoneytracker.enums.TransactionType;

import java.util.Date;
import java.util.UUID;


public class Transaction {


    private final UUID uuid;
    private final Float amount;
    private final TransactionType transactionType;
    private final String description;
    private final Date transactionDate;

    public Transaction(UUID uuid, Float amount, TransactionType transactionType, String description, Date transactionDate) {
        this.uuid = uuid;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
        this.transactionDate = transactionDate;
    }


    public UUID getUuid() {
        return uuid;
    }

    public Float getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getDescription() {
        return description;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}
