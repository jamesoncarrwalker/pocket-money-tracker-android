package com.example.pocketmoneytracker.abstractClasses;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pocketmoneytracker.enums.ApiRequestMethod;
import com.example.pocketmoneytracker.helpers.MixedMapValuesToSingleType;
import com.example.pocketmoneytracker.interfaces.ResponseHandlerInterface;
import com.example.pocketmoneytracker.interfaces.ResponseObjectInterface;
import com.example.pocketmoneytracker.utils.Logging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiVolleyRequestObject extends AbstractApiRequest {

    public Map<String, Object> apiResponse;
    private RequestQueue requestQueue;
    //don't affect the original data
    private String jsonPostString;


    public ApiVolleyRequestObject(String baseUrl, ResponseObjectInterface responseObject, Context context) {
        super(baseUrl, responseObject, context);
    }

    public ApiVolleyRequestObject(String baseUrl, ResponseObjectInterface responseObject, Context context, ResponseHandlerInterface responseHandler) {
        super(baseUrl, responseObject, context, responseHandler);
    }


    @Override
    public void sendRequest(ApiRequestMethod requestMethod, String endPoint, Map<String, Object> rawData) {
        super.sendRequest(requestMethod, endPoint, rawData);
        this.submitRequest();
    }

    @Override
    public void sendRequest(ApiRequestMethod requestMethod, String endPoint) {
        super.sendRequest(requestMethod, endPoint);
        this.submitRequest();
    }


    @Override
    public void setRequestData() {
        if (null == this.rawData) {
            return;
        }
        //turn the post into a json object to maintain data types
        this.jsonPostString = String.valueOf(new JSONObject(this.rawData));
    }

    private void submitRequest() {
        setupRequestQueue();
        setRequestUrl();
        setRequestData();
        if (this.requestMethod == ApiRequestMethod.GET) {

            final StringRequest stringRequest = buildStringRequestObject();
            stringRequest.setShouldCache(false);
            this.requestQueue.add(stringRequest);

        } else {

//            final JsonRequest jsonRequest = buildJsonRequestObject();
//            jsonRequest.setShouldCache(false);
//            this.requestQueue.add(jsonRequest);
        }
        this.resetRequest();
    }

    private void handleApiSuccessResponse(JSONObject jsonResponse) {

        this.responseObject.parseResponse(jsonResponse);
        this.apiResponse = this.responseObject.getResponse();

        if (null != this.responseHandler) {
            this.responseHandler.passResponse(this.apiResponse);
        }
    }

    private void handleApiErrorResponse(VolleyError error) {

    }

    private void setupRequestQueue() {

        if (null == this.requestQueue) {
            this.requestQueue = Volley.newRequestQueue(context);
        }

    }

    private void setResponse(String response) {
        this.responseObject.parseResponse(response);
    }

    private JsonRequest buildJsonRequestObject() {
        return null;
    }

    private StringRequest buildStringRequestObject() {
        final Map<String, Object> finalData = this.rawData;
        return new StringRequest(getVolleyRequestMethod(), this.requestUrl,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        handleApiSuccessResponse(new JSONObject(response));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    handleApiErrorResponse(error);
                    Logging.logStr("API error",error.toString());
                }
            }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (null != finalData && 0 != finalData.size()) {
                    return MixedMapValuesToSingleType.convertValuesToString(finalData);
                }
                return params;
            }
        };
    }

    private int getVolleyRequestMethod() {

        switch (this.requestMethod) {
            case POST:
                return Request.Method.POST;
            default:
                return Request.Method.GET;
        }
    }

}

