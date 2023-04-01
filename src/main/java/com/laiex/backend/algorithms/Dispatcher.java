package com.laiex.backend.algorithms;

import com.laiex.backend.db.CarrierRepository;
import com.laiex.backend.db.entity.CarrierEntity;

public class Dispatcher {

    public static CarrierEntity dispatch(CarrierRepository carrierRepository, CarrierEntity.CarrierType carrierType, Double weight) {
        switch (carrierType) {
            // create RobotCar
            case RobotCar -> {
                if (weight < 1000) {
                    return CarrierEntity.carrierGenerator(carrierRepository, carrierType, weight);
                } else {
                    return null;
                }
            }
            // create UAV
            case UAV -> {
                if (weight < 200) {
                    return CarrierEntity.carrierGenerator(carrierRepository, carrierType, weight);
                } else {
                    return null;
                }
            }
            default -> {
                return null;
            }
        }
    }
}
