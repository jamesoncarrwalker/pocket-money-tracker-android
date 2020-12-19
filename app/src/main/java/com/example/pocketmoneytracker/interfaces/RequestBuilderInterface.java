package com.example.pocketmoneytracker.interfaces;

import com.example.pocketmoneytracker.enums.ApiRequestMethod;

public interface RequestBuilderInterface {

    void setRequestParameters(ApiRequestMethod method, String url, Object data);

    Object getRequestObject();
}
