package com.laiex.backend.db.entity;

import org.springframework.data.annotation.Id;

public record StationEntity(
        @Id Long id,
        String country,
        String city,
        String address,
        String status,
        Float latitude,
        Float longtitude
) {
}
