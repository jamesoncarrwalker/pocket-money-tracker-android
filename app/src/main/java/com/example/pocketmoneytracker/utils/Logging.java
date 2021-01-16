package com.example.pocketmoneytracker.utils;

import android.util.Log;

import java.util.Map;

public class Logging {

    public static void logInt(String tag, int value) {
        Log.i("LOGI " + tag, String.valueOf(value));
    }

    public static void logFloat(String tag, Float value) {
        Log.i("LOGI " + tag, String.valueOf(value));
    }

    public static void logStr(String tag, String value) {
        Log.i("LOGI " + tag, String.valueOf(value));
    }

    public static void logMap(String tag, Map<String, Object> value, boolean showKeys) {
        Log.i("LOGI" + tag, "START LOGGING MAP");
        if(showKeys) {
            for (Map.Entry<String, Object> entry : value.entrySet()) {
                String key = entry.getKey();
                String val = String.valueOf(entry.getValue());
                Log.i("LOGI" + tag, "key: " + key + " - Val: " + val);
            }
        } else {
            for (Object val : value.values()) {
                Log.i("LOGI" + tag, String.valueOf(val));
            }
        }
        Log.i("LOGI" + tag, "END LOGGING MAP");


    }
}
