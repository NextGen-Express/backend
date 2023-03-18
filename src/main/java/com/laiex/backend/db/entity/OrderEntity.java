package com.laiex.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalTime;

@Table("orders")
public record OrderEntity(
        @Id Long id,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("order_time") LocalTime orderTime,
        @JsonProperty("estimated_pick_time") LocalTime estimatedPickTime,
        @JsonProperty("estimated_delivery_time") LocalTime estimatedDeliveryTime,
        @JsonProperty("pickup_addr") String pickupAddr,
        @JsonProperty("delivery_addr") String deliveryAddr,
        @JsonProperty("carrier_id") Long carrierId,
        Double price,
        String status


) {
}
