package com.laiex.backend.algorithms;

import com.laiex.backend.db.entity.CarrierEntity;
import com.laiex.backend.db.entity.StationEntity;
import com.laiex.backend.model.responsebody.PlanDetails;
import com.laiex.backend.model.responsebody.SearchResponse;
import com.laiex.backend.service.outside.GoogleService;
import com.laiex.backend.service.SearchService;

public class RoutePlanning {
    SearchService searchService;
    GoogleService googleService;
    String origin;
    String destination;
    Double weight;

    public RoutePlanning(SearchService searchService, GoogleService googleService, String origin, String destination, Double weight) {
        this.searchService = searchService;
        this.googleService = googleService;
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public SearchResponse getPlanDetails() {
        try {
            // get the closest station
            StationEntity station = StationEntity.getClosestStation(googleService, origin);
            System.out.println("The station picked is " + station.address());

            // get the plan details
            PlanDetails groundPlan = searchService.getPlanDetails(station, CarrierEntity.CarrierType.RobotCar,origin, destination, weight);
            PlanDetails uavPlan = searchService.getPlanDetails(station, CarrierEntity.CarrierType.UAV,origin, destination, weight);
            return new SearchResponse(groundPlan, uavPlan);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //
}
