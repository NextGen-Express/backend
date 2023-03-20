package com.laiex.backend.db.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("carriers")
public record CarrierEntity(
        @Id Long id,
        @JsonProperty("shipping_method") Enum shippingMethod,
        Integer burden,
        Integer capacity,
        Integer battery
) {
    public static enum CarrierType {
        RobotCar, UAV
    }
}
