package com.example.pocketmoneytracker.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pocketmoneytracker.interfaces.ResponseHandlerInterface;


public class AddTransactionActivity extends AppCompatActivity implements ResponseHandlerInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void passResponse(Object response) {

    }
}
