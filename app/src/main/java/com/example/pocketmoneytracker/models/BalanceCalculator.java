package com.example.pocketmoneytracker.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.pocketmoneytracker.enums.TransactionType;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class BalanceCalculator {

    private Map<String, Transaction> transactionsMap;
    private ArrayList<Transaction> transactionsArray;
    private Float credit, debit, balance;

    public BalanceCalculator(Float credit, Float debit) {
        this.credit = credit;
        this.debit = debit;
    }

    public BalanceCalculator(Map<String, Transaction> transactionsMap) {
        this.transactionsMap = transactionsMap;
    }

    public BalanceCalculator(ArrayList<Transaction> transactionsArray) {
        this.transactionsArray = transactionsArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Float getBalance() {
        if (null == this.balance) {
            try {
                calculateBalance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return this.balance;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calculateBalance() throws Exception {
        if (null == credit && null == debit && null == transactionsMap && null == transactionsArray) {
            throw new Exception("No information to calculate a balance");
        }

        if (null == transactionsMap && null == transactionsArray) {
            calculateFromCreditDebit();
        } else {
            calculateFromTransactions();
        }
    }

    private void calculateFromCreditDebit() {
        this.balance = this.credit - this.debit;
    }

    public TransactionType getBalanceType() {
        if (null == this.balance || this.balance >= 0) {
            return TransactionType.BLACK;
        }

        return TransactionType.SPEND;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calculateFromTransactions() {

        if (null == transactionsArray) {
//            this.balance = getTotal(this.transactionsMap.entrySet());
        } else {
            this.balance = getTotal(this.transactionsArray);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Float getTotal(ArrayList<Transaction> transactions) {
        if (null == transactions || 0 == transactions.size()) {
            return null;
        }

        AtomicReference<Float> total = new AtomicReference<>(0f);
        transactions.forEach((transaction) -> {
            if (transaction.getTransactionType() == TransactionType.CREDIT) {
                total.set(total.get() + transaction.getAmount());
            } else {
                total.set(total.get() - transaction.getAmount());
            }
        });

        return total.get();
    }

}
