package com.laiex.backend.model.responsebody;

import com.laiex.backend.db.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public record HistoryOrderBody(
        Long orderId,
        LocalDateTime orderTime,
        LocalDateTime estimatedPickTime,
        LocalDateTime estimatedDeliveryTime,
        String pickupAddr,
        String deliveryAddr,
        Long carrierId,
        Double price,
        OrderEntity.OrderStatus status
) {
}
