package com.example.pocketmoneytracker.abstractClasses;

import com.example.pocketmoneytracker.interfaces.ResponseObjectInterface;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResponseObject implements ResponseObjectInterface {

    protected Object rawResponse;
    protected Map<String,Object> parsedResponse = new HashMap<>();

    @Override
    public void setRawResponse(Object response) {
        this.rawResponse = response;
    }

    @Override
    public void parseResponse(Object response) {
        this.setRawResponse(response);
    }

    @Override
    public void parseResponse(JSONObject response) {
        this.setRawResponse(response);
    }

    @Override
    public Map<String, Object> getResponse() {

        return this.parsedResponse;
    }

    @Override
    public void clearResponse() {
        this.parsedResponse = new HashMap<>();
        this.rawResponse = null;
    }
}
