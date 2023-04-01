package com.laiex.backend.algorithms;

import com.laiex.backend.db.entity.CarrierEntity;
import com.laiex.backend.db.entity.StationEntity;
import com.laiex.backend.model.responsebody.PlanDetails;
import com.laiex.backend.model.responsebody.ResponseHandler;
import com.laiex.backend.model.responsebody.SearchResponse;
import com.laiex.backend.service.GoogleService;
import com.laiex.backend.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
        // get ground plan
        try {
            // get the closest station
            Object[] closestStation = StationEntity.getClosestStation(googleService, origin);
            StationEntity station = (StationEntity) closestStation[0];
            System.out.println("The station picked is " + station.address());
            double distanceToStation = (double) closestStation[1];
            double timeToStation = (double) closestStation[2];

            // get the plan details
            PlanDetails groundPlan = searchService.getPlanDetails(station, CarrierEntity.CarrierType.RobotCar,origin, destination, weight);
            PlanDetails uavPlan = searchService.getPlanDetails(station, CarrierEntity.CarrierType.UAV,origin, destination, weight);
            SearchResponse response = new SearchResponse(groundPlan, uavPlan);
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //
}
