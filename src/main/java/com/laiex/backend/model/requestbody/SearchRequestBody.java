package com.laiex.backend.model.requestbody;

public record SearchRequestBody(
        String origin,
        String destination,
        Double weight
) {
}
