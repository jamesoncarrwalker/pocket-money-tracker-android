package com.example.pocketmoneytracker.abstractClasses;

import com.example.pocketmoneytracker.interfaces.ResponseObjectInterface;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResponseObject implements ResponseObjectInterface {

    protected Object rawResponse;
    protected Map<String, Object> parsedResponse = new HashMap<>();

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

    ///::TODO SET A FIELDS TO SEARCH FOR REQUIREMENT IN THE INTERFACE TO BE IMPLEMENTED BY THE CHILD CLASS
    ///::TODO THEN CHECK THE FIELD EXISTS THERE AND PROCESS CORRECTLY ADDING TO  parsedResponse
    ///::TODO ADD ANY FIELDS NOT SEARCHED FOR HERE IN PARSED RESPONSE
    ///::TODO REMOVE RAW RESPONSE AS ACHIEVING NOTHING AT ALL
}
