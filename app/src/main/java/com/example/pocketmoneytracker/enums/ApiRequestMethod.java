package com.example.pocketmoneytracker.enums;

public enum ApiRequestMethod {
    POST("POST"),
    GET("GET");

    final private String var;

    ApiRequestMethod(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }
}


