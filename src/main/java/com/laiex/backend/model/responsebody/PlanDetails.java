package com.laiex.backend.model.responsebody;

import com.laiex.backend.db.entity.CarrierEntity;

import java.time.LocalDateTime;

public record PlanDetails(
        String stationAddress, Long carrierId,
        CarrierEntity.CarrierType carrierType,
        String origin,
        String destionation,
        Double distance,
        Double weight,
        LocalDateTime estimatedPickTime,
        LocalDateTime estimatedDeliveryTime,
        Double price) {
}
