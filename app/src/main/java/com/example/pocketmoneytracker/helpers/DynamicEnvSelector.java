package com.example.pocketmoneytracker.helpers;

import android.os.Build;

import com.example.pocketmoneytracker.enums.DynamicEnvValue;
import com.example.pocketmoneytracker.enums.EnvVar;
import com.example.pocketmoneytracker.utils.Logging;

import java.io.File;

public class DynamicEnvSelector {

    public static EnvVar EnvVar(DynamicEnvValue envVar) {

        boolean dev = isProbablyEmulator();

        if(envVar == DynamicEnvValue.API_BASE) {
            return dev ? EnvVar.DEV : EnvVar.PROD;
        }
        return null;
    }

    //Shamelessly lifted from https://stackoverflow.com/a/55355049
    public static Boolean isProbablyEmulator() {

        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
            || Build.FINGERPRINT.startsWith("generic")
            || Build.FINGERPRINT.startsWith("unknown")
            || Build.HARDWARE.contains("goldfish")
            || Build.HARDWARE.contains("ranchu")
            || Build.MODEL.contains("google_sdk")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.MANUFACTURER.contains("Genymotion")
            || Build.PRODUCT.contains("sdk_google")
            || Build.PRODUCT.contains("google_sdk")
            || Build.PRODUCT.contains("sdk")
            || Build.PRODUCT.contains("sdk_x86")
            || Build.PRODUCT.contains("vbox86p")
            || Build.PRODUCT.contains("emulator")
            || Build.PRODUCT.contains("simulator");

    }

}