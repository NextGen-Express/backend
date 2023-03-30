package com.laiex.backend.model;

public record SearchRequestBody(
        String origin,
        String destination,
        Double capacity
) {
}
