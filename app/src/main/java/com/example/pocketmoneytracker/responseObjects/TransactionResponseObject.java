package com.example.pocketmoneytracker.responseObjects;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.pocketmoneytracker.abstractClasses.AbstractResponseObject;
import com.example.pocketmoneytracker.enums.TransactionType;
import com.example.pocketmoneytracker.helpers.DateConverter;
import com.example.pocketmoneytracker.models.Transaction;

public class TransactionResponseObject extends AbstractResponseObject {


    @Override
    public void parseResponse(JSONObject response) {
        Map<String, Transaction> parsedResponse = new HashMap<>();

        try {
            JSONArray transactionsArray = response.getJSONArray("transactions");

            int len = transactionsArray.length();

            for (int i = 0; i < len; i++) {
                Date transactionDate = new Date();
                JSONObject jsonTransaction = transactionsArray.getJSONObject(i);
                UUID uuid = UUID.fromString(jsonTransaction.getString("UUID"));
                Float amount = Float.parseFloat(jsonTransaction.getString("transaction_amount"));
                TransactionType transactionType = TransactionType.CREDIT;

                try {
                    transactionType = TransactionType.valueOf(jsonTransaction.getString("transaction_type"));
                } catch (Exception e) {
                    Log.d("DATE ERROR", "Could not find a transaction type for  " + jsonTransaction.getString("transaction_type"));
                    continue;
                }

                String description = jsonTransaction.getString("transaction_description");
                try {
                    transactionDate = DateConverter.dateFromString(jsonTransaction.getString("transaction_added"), "E d M");
                } catch (ParseException e) {
                    Log.d("DATE ERROR", "Could not parse the date for " + jsonTransaction.getString("UUID"));
                }

                Transaction transaction = new Transaction(uuid, amount, transactionType, description, transactionDate);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void parseResponse(Object response) {

    }

}

