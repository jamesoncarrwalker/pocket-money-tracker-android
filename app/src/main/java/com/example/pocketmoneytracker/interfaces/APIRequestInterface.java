package com.example.pocketmoneytracker.interfaces;

import java.util.Map;

import com.example.pocketmoneytracker.enums.ApiRequestMethod;

public interface APIRequestInterface {

    void sendRequest(ApiRequestMethod requestMethod, String endPoint, Map<String, Object> rawData);

    void sendRequest(ApiRequestMethod requestMethod, String endPoint);

    ResponseObjectInterface getResponse();

    void resetRequest();

    void setRequestUrl();

    void setRequestData();

}
