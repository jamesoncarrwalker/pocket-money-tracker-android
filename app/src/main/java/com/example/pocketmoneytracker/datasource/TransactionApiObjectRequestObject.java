package com.example.pocketmoneytracker.datasource;

import android.content.Context;

import com.example.pocketmoneytracker.abstractClasses.ApiVolleyRequestObject;
import com.example.pocketmoneytracker.enums.ApiRequestMethod;
import com.example.pocketmoneytracker.interfaces.ResponseHandlerInterface;
import com.example.pocketmoneytracker.interfaces.ResponseObjectInterface;

public class TransactionApiObjectRequestObject extends ApiVolleyRequestObject {


    public TransactionApiObjectRequestObject(String baseUrl, ResponseObjectInterface responseObject, Context context) {
        super(baseUrl, responseObject, context);
    }

    public TransactionApiObjectRequestObject(String baseUrl, ResponseObjectInterface responseObject, Context context, ResponseHandlerInterface responseHandler) {
        super(baseUrl, responseObject, context, responseHandler);
    }

    public void getTransactions() {
        this.sendRequest(ApiRequestMethod.GET, "/transactions");
    }
}
