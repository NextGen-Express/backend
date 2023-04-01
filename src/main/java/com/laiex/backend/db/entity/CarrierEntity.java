package com.laiex.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laiex.backend.db.CarrierRepository;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("carriers")
public record CarrierEntity(
        @Id Long id,
        @JsonProperty("shipping_method") CarrierType type,
        Double burden,
        Double capacity,
        Integer battery
) {
    public enum CarrierType {
        RobotCar, UAV;
    }

    public CarrierEntity(Long id, CarrierType type, Double burden, Double capacity, Integer battery) {
        this.id = id;
        this.type = type;
        this.burden = burden;
        this.capacity = capacity;
        this.battery = battery;
    }

    public CarrierEntity save(CarrierRepository repository) {
        return repository.save(this);
    }

    public static CarrierEntity carrierGenerator(CarrierRepository carrierRepository, CarrierType carrierType, Double weight) {
        double capacity = carrierType == CarrierType.RobotCar ? 1000.0 : 200.0;
        carrierRepository.insertNewCarrier(carrierType, weight, capacity, Integer.MAX_VALUE);
        long id = carrierRepository.getId();
        return new CarrierEntity(id, carrierType, weight, capacity - weight, Integer.MAX_VALUE);
    }

}
