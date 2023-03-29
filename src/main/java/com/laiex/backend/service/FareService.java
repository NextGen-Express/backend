package com.laiex.backend.service;

import org.springframework.stereotype.Service;

@Service
public class FareService {
    public Double computeFare(Double distance, Double capacity, Boolean isUav) {
        Double distanceFactor = distance / 1000.0;
        Double capacityFactor = Math.log(capacity);
        if (isUav) {
            return 2.0 * distanceFactor * capacityFactor;
        } else {
            return distanceFactor * capacityFactor;
        }
    }
}
