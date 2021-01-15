package com.example.pocketmoneytracker.abstractClasses;

import android.support.v7.app.AppCompatActivity;

import com.example.pocketmoneytracker.interfaces.ApiRequestActivityInterface;
import com.example.pocketmoneytracker.interfaces.ResponseHandlerInterface;

public abstract class AbstractRequestActivity extends AppCompatActivity implements ResponseHandlerInterface, ApiRequestActivityInterface {
}
