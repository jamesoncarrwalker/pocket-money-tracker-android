package com.example.pocketmoneytracker.interfaces;

import com.example.pocketmoneytracker.enums.ApiRequestMethod;

import java.util.Map;

public interface APIRequestInterface {

    void sendRequestWithData(ApiRequestMethod requestMethod, String endPoint, Map<String, Object> rawData);

    void sendRequest(ApiRequestMethod requestMethod, String endPoint);

    ResponseObjectInterface getResponseObject();

    void resetRequest();

    void setRequestUrl();

    void setRequestData();

}
