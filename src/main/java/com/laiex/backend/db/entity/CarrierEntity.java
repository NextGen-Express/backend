package com.laiex.backend.db.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("carriers")
public record CarrierEntity(
        @Id Long id,
        String shipping_method,
        Integer burden,
        Integer capacity,
        Integer battery
) {
}
