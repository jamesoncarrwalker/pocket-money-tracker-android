package com.example.pocketmoneytracker.responseObjects;

import android.util.Log;

import com.example.pocketmoneytracker.abstractClasses.AbstractResponseObject;
import com.example.pocketmoneytracker.enums.TransactionType;
import com.example.pocketmoneytracker.helpers.DateConverter;
import com.example.pocketmoneytracker.models.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class TransactionResponseObject extends AbstractResponseObject {

    private ArrayList<Transaction> transactions;

    @Override
    public void parseResponse(JSONObject response) {
        super.parseResponse(response);
        ArrayList<Transaction> transactionsFromResponse = new ArrayList<>();
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
                    transactionDate = DateConverter.dateFromString(jsonTransaction.getString("transaction_added"), "yyyy-MM-dd HH:mm:ss");
                } catch (ParseException e) {
                    Log.d("DATE ERROR", "Could not parse the date for " + jsonTransaction.getString("transaction_added"));
                }

                Transaction transaction = new Transaction(uuid, amount, transactionType, description, transactionDate);
                transactionsFromResponse.add(0, transaction);
            }

            this.parsedResponse.put("transactions",transactionsFromResponse);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}


