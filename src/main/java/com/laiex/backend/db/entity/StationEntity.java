package com.laiex.backend.db.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("stations")
public record StationEntity(
        @Id Long id,
        String address,
        String city,
        String country,
        Float latitude,
        Float longitude,
        String status
) {
}

