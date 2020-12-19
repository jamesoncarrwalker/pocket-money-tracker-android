package com.example.pocketmoneytracker.interfaces;

import org.json.JSONObject;

import java.util.Map;

public interface ResponseObjectInterface {

    void parseResponse(Object response);

    void parseResponse(JSONObject response);

    Map<String, Object> getResponse();

    void clearResponse();
}
