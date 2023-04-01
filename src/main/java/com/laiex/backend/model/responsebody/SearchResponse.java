package com.laiex.backend.model.responsebody;

public record SearchResponse(
        PlanDetails groundPlan,
        PlanDetails uavPlan
) {
}
