package com.example.pocketmoneytracker.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pocketmoneytracker.R;
import com.example.pocketmoneytracker.enums.EnvVar;
import com.example.pocketmoneytracker.enums.TransactionType;
import com.example.pocketmoneytracker.helpers.NumberToStringConverter;
import com.example.pocketmoneytracker.helpers.ViewFormatter;
import com.example.pocketmoneytracker.models.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

    ArrayList<Transaction> transactions;

    public TransactionAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionView, amountView, dateView;
        public ImageView transactionTypeIconView;
        public ConstraintLayout transactionWrapper;

        public ViewHolder(View view) {
            super(view);
            //content views
            descriptionView = view.findViewById(R.id.transaction_description_view);
            amountView = view.findViewById(R.id.transaction_amount_view);
            dateView = view.findViewById(R.id.transaction_date_view);
            transactionTypeIconView = view.findViewById(R.id.transaction_type_icon_holder);
            //layout wrappers
            transactionWrapper = view.findViewById(R.id.transaction_wrapper);

        }
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentLayout, int i) {
        return new ViewHolder(LayoutInflater.from(parentLayout.getContext()).inflate(R.layout.item_view_transaction, parentLayout, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder transactionDisplayHolder, int transactionArrayPosition) {
        Transaction currentTransaction = this.transactions.get(transactionArrayPosition);
        transactionDisplayHolder.descriptionView.setText(currentTransaction.getDescription());
        transactionDisplayHolder.dateView.setText(currentTransaction.getFriendlyDateString());
        ViewFormatter.formatTransactionTextView(transactionDisplayHolder.amountView, currentTransaction.getTransactionType(),currentTransaction.getAmount());

    }

    @Override
    public int getItemCount() {
        if(null == transactions) {
            return 0;
        }
        return transactions.size();
    }
}
