package com.laiex.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Table("orders")
public record OrderEntity(
        @Id Long id,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("order_time") LocalDateTime orderTime,
        @JsonProperty("estimated_pick_time") LocalDateTime estimatedPickTime,
        @JsonProperty("estimated_delivery_time") LocalDateTime estimatedDeliveryTime,
        @JsonProperty("pickup_addr") String pickupAddr,
        @JsonProperty("delivery_addr") String deliveryAddr,
        @JsonProperty("carrier_id") Long carrierId,
        Double price,
        OrderStatus status,
        @JsonProperty("strpie_product_id") String stripProductId
) {
    public enum OrderStatus {ordered, pickup, delivered, reviewed};
}

