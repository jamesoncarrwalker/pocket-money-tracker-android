package com.example.pocketmoneytracker.enums;

public enum TransactionType {
    BLACK("BLACK"),
    CREDIT("CREDIT"),
    SPEND("SPEND");

    final private String var;

    TransactionType(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }
}
