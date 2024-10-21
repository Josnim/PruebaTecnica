package com.example.pruebatecnica.utils;

import java.util.HashMap;
import java.util.Map;

public class MovimientosUtils {
    public static Map<String, Object> MakeResponse(String status, String message, Object responseObj) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        response.put("data", responseObj);
        return response;

    }
}
