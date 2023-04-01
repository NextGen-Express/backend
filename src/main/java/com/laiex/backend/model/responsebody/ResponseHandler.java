package com.laiex.backend.model.responsebody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String key, Object body) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, body);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
}
