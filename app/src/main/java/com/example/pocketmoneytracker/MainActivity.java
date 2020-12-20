package com.example.pocketmoneytracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pocketmoneytracker.adapter.TransactionAdapter;
import com.example.pocketmoneytracker.datasource.TransactionApiObjectRequestObject;
import com.example.pocketmoneytracker.enums.EnvVar;
import com.example.pocketmoneytracker.interfaces.ResponseHandlerInterface;
import com.example.pocketmoneytracker.models.Transaction;
import com.example.pocketmoneytracker.responseObjects.TransactionResponseObject;
import com.example.pocketmoneytracker.utils.Logging;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ResponseHandlerInterface {

    TransactionApiObjectRequestObject transactionApiObject;
    RecyclerView basicRecycler;
    ArrayList<Transaction> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transactionApiObject = new TransactionApiObjectRequestObject(EnvVar.PROD.getVar(), new TransactionResponseObject(),this, this);

        checkForTransactions();

    }

    private void setupRecycler() {
        basicRecycler = findViewById(R.id.skeleton_recycler_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        basicRecycler.setLayoutManager(manager);
    }

    private void updateAdapter() {
        if(null == basicRecycler) {
            setupRecycler();
        }
        basicRecycler.setAdapter(null);
        basicRecycler.setAdapter(new TransactionAdapter(transactions));
    }

    private void checkForTransactions() {
        if(null == transactionApiObject) return;
        transactionApiObject.getTransactions();
    }

    @Override
    public void passResponse(Object response) {
        if(response instanceof Map) {
            Map map = (Map) response;
            ArrayList<Transaction> values = new ArrayList<>(map.values());
            this.transactions = values;
            updateAdapter();
        }
    }

    public void getTransactions() {
        this.transactionApiObject.getTransactions();
    }
}
