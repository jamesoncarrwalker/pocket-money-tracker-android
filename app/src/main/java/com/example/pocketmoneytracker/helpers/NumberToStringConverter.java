package com.example.pocketmoneytracker.helpers;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.example.pocketmoneytracker.enums.TransactionType;

import java.text.DecimalFormat;

public class NumberToStringConverter {

    @SuppressLint("DefaultLocale")
    public static String getDecimalStringFromFloat(Float amount, String decimalPattern) {
        if(null == amount) {
            return String.format(decimalPattern, 0f);
        }
        return String.format(decimalPattern, amount);
    }

    public static String getTransactionStringFromFloat(Float amount, TransactionType transactionType, String decimalPattern, String currencySymbol) {
        String amountString = currencySymbol + NumberToStringConverter.getDecimalStringFromFloat(amount,decimalPattern);

        if(TransactionType.SPEND == transactionType) {
            amountString = "-" + amountString;
        }

        return amountString;
    }
}
