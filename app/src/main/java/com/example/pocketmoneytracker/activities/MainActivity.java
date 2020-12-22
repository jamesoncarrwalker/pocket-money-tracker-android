package com.example.pocketmoneytracker.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pocketmoneytracker.R;
import com.example.pocketmoneytracker.adapter.TransactionAdapter;
import com.example.pocketmoneytracker.datasource.TransactionApiObjectRequestObject;
import com.example.pocketmoneytracker.enums.DynamicEnvValue;
import com.example.pocketmoneytracker.enums.EnvVar;
import com.example.pocketmoneytracker.enums.TransactionType;
import com.example.pocketmoneytracker.helpers.ActivityLauncher;
import com.example.pocketmoneytracker.helpers.DynamicEnvSelector;
import com.example.pocketmoneytracker.helpers.NumberToStringConverter;
import com.example.pocketmoneytracker.helpers.ViewFormatter;
import com.example.pocketmoneytracker.interfaces.ResponseHandlerInterface;
import com.example.pocketmoneytracker.models.BalanceCalculator;
import com.example.pocketmoneytracker.models.Transaction;
import com.example.pocketmoneytracker.responseObjects.TransactionResponseObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ResponseHandlerInterface {

    TransactionApiObjectRequestObject transactionApiObject;
    RecyclerView basicRecycler;
    TextView transactionAmountView;
    Float totalBalance;
    TransactionType totalBalanceTransactionType;
    ArrayList<Transaction> transactions = new ArrayList<>();
    ActivityLauncher activityLauncher;
    BalanceCalculator balanceCalculator;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateBalance();
        getTransactions();

    }

    private void setupRecycler() {
        basicRecycler = findViewById(R.id.skeleton_recycler_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        basicRecycler.setLayoutManager(manager);
    }

    private void updateAdapter() {
        if (null == basicRecycler) {
            setupRecycler();
        }
        basicRecycler.setAdapter(null);
        basicRecycler.setAdapter(new TransactionAdapter(transactions));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateBalance() {
        if (null == this.transactionAmountView) {
            this.transactionAmountView = findViewById(R.id.balance_view);
        }
        if (null == this.transactions || this.transactions.size() < 1) {
            this.totalBalance = null;
            this.totalBalanceTransactionType = TransactionType.BLACK;
        } else {
            if (null == this.balanceCalculator) {
                this.balanceCalculator = new BalanceCalculator(this.transactions);
            }
            this.totalBalance = this.balanceCalculator.getBalance();
            this.totalBalanceTransactionType = this.balanceCalculator.getBalanceType();
        }

        ViewFormatter.formatTransactionTextView(this.transactionAmountView, this.totalBalanceTransactionType, this.totalBalance);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void passResponse(Object response) {
        if (response instanceof Map) {
            Map map = (Map) response;
            ArrayList<Transaction> values = new ArrayList<>(map.values());
            this.transactions = values;
            updateAdapter();
            updateBalance();
        }
    }

    public void getTransactions() {
        if (null == this.transactionApiObject) {
            initTransactionApiObject();
        }
        this.transactionApiObject.getTransactions();
    }

    public void addTransactionLauncher(View view) {
        if (null == this.activityLauncher) {
            this.activityLauncher = new ActivityLauncher();
        }

        activityLauncher.launchActivity("activities", "AddTransactionActivity", this, null);
    }

    private void updateBalanceView() {
        String balanceString = NumberToStringConverter.getTransactionStringFromFloat(this.totalBalance, this.totalBalanceTransactionType, EnvVar.CURRENCY_STRING_PATTERN.getVar(), EnvVar.CURRENCY_SYMBOL.getVar());

        this.transactionAmountView.setText(balanceString);
    }

    private void initTransactionApiObject() {
        EnvVar apiBase = DynamicEnvSelector.EnvVar(DynamicEnvValue.API_BASE);
        assert apiBase != null;
        this.transactionApiObject = new TransactionApiObjectRequestObject(apiBase.getVar(), new TransactionResponseObject(), this, this);
    }
}
