package com.example.pocketmoneytracker.enums;

public enum VolleyRequestType {

    STRING("string-request"),
    MULTI_PART("multi-part-request"),
    JSON("json-request");


    final private String var;

    VolleyRequestType(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }
}
