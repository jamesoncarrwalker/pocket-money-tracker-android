package com.example.pocketmoneytracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pocketmoneytracker.datasource.TransactionApiObjectRequestObject;
import com.example.pocketmoneytracker.enums.EnvVar;
import com.example.pocketmoneytracker.interfaces.ResponseHandlerInterface;
import com.example.pocketmoneytracker.responseObjects.TransactionResponseObject;

public class MainActivity extends AppCompatActivity implements ResponseHandlerInterface {

    TextView helloWorld;
    Button submitButton;
    TransactionApiObjectRequestObject transactionApiObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloWorld = findViewById(R.id.hello_world_text_view);
        submitButton = findViewById(R.id.submit_button);
        transactionApiObject = new TransactionApiObjectRequestObject(EnvVar.PROD.getVar(), new TransactionResponseObject(),this, this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTransactions();
            }
        });

    }

    @Override
    public void passResponse(Object response) {
        helloWorld.setText(response.toString());
    }

    public void getTransactions() {
        this.transactionApiObject.getTransactions();
    }
}
