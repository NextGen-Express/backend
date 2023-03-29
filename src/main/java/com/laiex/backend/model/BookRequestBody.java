package com.laiex.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laiex.backend.db.entity.OrderEntity;

import java.time.LocalTime;
public record BookRequestBody(
        @JsonProperty("user_id") Long userId,
        @JsonProperty("order_time") LocalTime orderTime,
        @JsonProperty("estimated_pick_time") LocalTime estimatedPickTime,
        @JsonProperty("estimated_delivery_time") LocalTime estimatedDeliveryTime,
        @JsonProperty("pickup_addr") String pickupAddr,
        @JsonProperty("delivery_addr") String deliveryAddr,
        @JsonProperty("carrier_id") Long carrierId,
        Double price,
        OrderEntity.Status status

) {
}
