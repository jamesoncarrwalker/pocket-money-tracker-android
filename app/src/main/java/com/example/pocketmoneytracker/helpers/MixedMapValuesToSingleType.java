package com.example.pocketmoneytracker.helpers;

import java.util.HashMap;
import java.util.Map;

public class MixedMapValuesToSingleType {

    public static Map<String, String> convertValuesToString(Map<String, Object> originalMap) {
        Map<String, String> mapToReturn = new HashMap<>();
        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            mapToReturn.put(key, String.valueOf(value));

        }
        return mapToReturn;
    }
}
