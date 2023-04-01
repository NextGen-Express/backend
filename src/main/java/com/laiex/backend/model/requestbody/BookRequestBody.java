package com.laiex.backend.model.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laiex.backend.db.entity.OrderEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
public record BookRequestBody(
        @JsonProperty("order_time") LocalDateTime orderTime,
        @JsonProperty("estimated_pick_time") LocalDateTime estimatedPickTime,
        @JsonProperty("estimated_delivery_time") LocalDateTime estimatedDeliveryTime,
        @JsonProperty("pickup_addr") String pickupAddr,
        @JsonProperty("delivery_addr") String deliveryAddr,
        @JsonProperty("carrier_id") Long carrierId,
        Double price,
        OrderEntity.OrderStatus status
) {
}
