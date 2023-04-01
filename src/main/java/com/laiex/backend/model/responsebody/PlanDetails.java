package com.laiex.backend.model.responsebody;

import com.laiex.backend.db.entity.CarrierEntity;

import java.time.LocalDateTime;

public record PlanDetails(
        Long carrierId,
        CarrierEntity.CarrierType carrierType,
        Double distance,
        Double weight,
        LocalDateTime estimatedPickTime,
        LocalDateTime estimatedDeliveryTime,
        Double price,
        com.google.maps.model.DirectionsRoute route) {
}
