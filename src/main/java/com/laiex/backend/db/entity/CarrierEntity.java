package com.laiex.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laiex.backend.db.CarrierRepository;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("carriers")
public record CarrierEntity(
        @Id Long id,
        @JsonProperty("shipping_method") CarrierType shippingMethod,
        Integer burden,
        Integer capacity,
        Integer battery
) {
    public CarrierEntity(Long id, String shippingMethod, Integer burden, Integer capacity, Integer battery) {
        this(id, CarrierType.fromString(shippingMethod), burden, capacity, battery);
    }

    public CarrierEntity save(CarrierRepository repository) {
        return repository.save(this);
    }

    public enum CarrierType {
        RobotCar, UAV;

        public static CarrierType fromString(String method) {
            if (method.equalsIgnoreCase("RobotCar")) {
                return CarrierType.RobotCar;
            } else if (method.equalsIgnoreCase("UAV")) {
                return CarrierType.UAV;
            } else {
                throw new IllegalArgumentException("Invalid shipping method: " + method);
            }
        }
    }
}
