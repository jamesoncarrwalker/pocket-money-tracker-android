package com.example.pocketmoneytracker.utils;

import android.util.Log;

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
}
