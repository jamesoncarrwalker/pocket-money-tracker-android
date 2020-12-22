package com.example.pocketmoneytracker.helpers;

import android.graphics.Color;
import android.widget.TextView;

import com.example.pocketmoneytracker.enums.EnvVar;
import com.example.pocketmoneytracker.enums.TransactionType;

public class ViewFormatter {

    public static void formatTransactionTextView(TextView transactionView, TransactionType transactionType, Float transactionAmount) {

        String amountString = NumberToStringConverter.getTransactionStringFromFloat(transactionAmount, transactionType, EnvVar.CURRENCY_STRING_PATTERN.getVar(), EnvVar.CURRENCY_SYMBOL.getVar());

        transactionView.setText(amountString);

        if (TransactionType.SPEND == transactionType) {
            transactionView.setTextColor(Color.RED);
        }

    }
}
