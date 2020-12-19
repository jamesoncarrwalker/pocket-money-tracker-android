package com.example.pocketmoneytracker.abstractClasses;

import java.util.HashMap;
import java.util.Map;

import com.example.pocketmoneytracker.interfaces.ResponseObjectInterface;

public abstract class AbstractResponseObject implements ResponseObjectInterface {

    protected Object rawResponse;
    protected Map<String,Object> parsedResponse = new HashMap<>();

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
