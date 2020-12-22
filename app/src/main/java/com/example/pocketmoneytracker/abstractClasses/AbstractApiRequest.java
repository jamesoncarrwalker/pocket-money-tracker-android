package com.example.pocketmoneytracker.abstractClasses;

import android.content.Context;

import com.example.pocketmoneytracker.enums.ApiRequestMethod;
import com.example.pocketmoneytracker.interfaces.APIRequestInterface;
import com.example.pocketmoneytracker.interfaces.ResponseHandlerInterface;
import com.example.pocketmoneytracker.interfaces.ResponseObjectInterface;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractApiRequest implements APIRequestInterface {

    final protected String baseUrl;
    final protected ResponseObjectInterface responseObject;
    final protected Context context;
    protected ResponseHandlerInterface responseHandler;

    protected String requestUrl;
    protected Map<String, Object> rawData = new HashMap<>();
    protected String endpoint;
    protected ApiRequestMethod requestMethod;

    public AbstractApiRequest(String baseUrl, ResponseObjectInterface responseObject, Context context) {
        this.baseUrl = baseUrl;
        this.responseObject = responseObject;
        this.context = context;
    }

    public AbstractApiRequest(String baseUrl, ResponseObjectInterface responseObject, Context context, ResponseHandlerInterface responseHandler) {
        this.baseUrl = baseUrl;
        this.responseObject = responseObject;
        this.context = context;
        this.responseHandler = responseHandler;
    }

    @Override
    public void sendRequest(ApiRequestMethod requestMethod, String endPoint, Map<String, Object> rawData) {
        this.requestMethod = requestMethod;
        this.endpoint = endPoint;
        this.rawData = rawData;

    }

    @Override
    public void sendRequest(ApiRequestMethod requestMethod, String endPoint) {
        this.requestMethod = requestMethod;
        this.endpoint = endPoint;
    }

    @Override
    public void setRequestUrl() {
        this.requestUrl = this.baseUrl + this.endpoint;
    }

    @Override
    public ResponseObjectInterface getResponseObject() {
        return this.responseObject;
    }

    @Override
    public void resetRequest() {
        this.requestMethod = null;
        this.rawData = null;
        this.endpoint = null;
        this.responseObject.clearResponse();
    }

}
