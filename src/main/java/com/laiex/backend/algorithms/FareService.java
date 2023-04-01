package com.laiex.backend.algorithms;

import com.laiex.backend.db.entity.CarrierEntity;
import org.springframework.stereotype.Service;

@Service
public class FareService {
    public Double computeFare(CarrierEntity.CarrierType carrierType, Double distance, Double weight) {
        double baseFare = 10.0;
        if(weight > 0.0 && weight <= 5.0) {
            baseFare += 0;
        } else if(weight > 5.0 && weight <= 20.0) {
            baseFare += 3 * Math.log(weight)/Math.log(1.5);
        } else if (weight > 20 && weight <= 50) {
            baseFare += 6.5 * Math.log(weight)/Math.log(2);
        } else if(weight > 50 && weight <= 100) {
            baseFare += 20 * Math.log(weight)/Math.log(2.5);
        } else if(weight > 100 && weight <= 200) {
            baseFare += 30 * Math.log(weight)/Math.log(3);
        } else {
            baseFare += 40 * Math.log(weight)/Math.log(3.5);
        }
        double distanceFactor = distance * 0.0004;
        double carrierTypeFactor = switch (carrierType) {
            case RobotCar -> 1.0;
            case UAV -> 1.15;
        };
        return (baseFare + distanceFactor) * carrierTypeFactor;
    }
}
