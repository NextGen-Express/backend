package com.laiex.backend.service;

import com.google.maps.model.DirectionsRoute;
import com.laiex.backend.algorithms.Dispatcher;
import com.laiex.backend.algorithms.FareService;
import com.laiex.backend.db.CarrierRepository;
import com.laiex.backend.db.entity.CarrierEntity;
import com.laiex.backend.db.entity.StationEntity;
import com.laiex.backend.model.responsebody.PlanDetails;
import com.laiex.backend.service.outside.GoogleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SearchService {
    private final GoogleService googleService;
    private final FareService fareService;
    private final CarrierRepository carrierRepository;

    public SearchService(GoogleService googleService, FareService fareService, CarrierRepository carrierRepository) {
        this.googleService = googleService;
        this.fareService = fareService;
        this.carrierRepository = carrierRepository;
    }

    public PlanDetails getPlanDetails(StationEntity station, CarrierEntity.CarrierType carrierType, String origin, String destination, Double weight) throws Exception {
        // get carrier instance if capacity is enough
        CarrierEntity carrier = Dispatcher.dispatch(carrierRepository, carrierType, weight);

        // if carrier is null, means out of capacity
        if(carrier == null) return null;

        // calculate distance and time from station to origin
        double[] distanceAndTimeFromStationToOrigin = carrierType == CarrierEntity.CarrierType.RobotCar ?
                googleService.calculateCarDistance(station.address(), origin) : googleService.calculateUAVDistance(station.address(), origin);

        // calculate distance and time from pick_up to drop_off
        double[] distanceAndTimeFromOriginToDest = carrierType == CarrierEntity.CarrierType.RobotCar ?
                googleService.calculateCarDistance(origin, destination) : googleService.calculateUAVDistance(origin, destination);

        // total distance
        double distance = distanceAndTimeFromStationToOrigin[0] + distanceAndTimeFromOriginToDest[0];
        // calculate estimated pick up time and estimated delivery time
        LocalDateTime estimatedPickTime = LocalDateTime.now().plusSeconds((long) distanceAndTimeFromStationToOrigin[1]);
        LocalDateTime estimatedDeliveryTime = estimatedPickTime.plusSeconds((long) distanceAndTimeFromOriginToDest[1]);

        // calculate fare
        Double fare = fareService.computeFare(carrierType, distance, weight);

        // calculate route
        DirectionsRoute route = carrierType == CarrierEntity.CarrierType.RobotCar ?
                googleService.getCarDirections(origin, destination) : null;
        return new PlanDetails(carrier.id(), carrierType, distance, weight, estimatedPickTime, estimatedDeliveryTime, fare, route);
    }
}
