package com.laiex.backend.service;

import com.google.maps.model.DirectionsRoute;
import com.google.maps.errors.ApiException;
import com.laiex.backend.model.PlanDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchService {
    private final GoogleService googleService;
    private final FareService fareService;

    public SearchService(GoogleService googleService, FareService fareService) {
        this.googleService = googleService;
        this.fareService = fareService;
    }

    public PlanDetails getPlanDetails(String origin, String destination, Double capacity) throws IOException, InterruptedException, ApiException {
        Double distance = googleService.calculateDistance(origin, destination);
        Double fare = fareService.computeFare(distance, capacity, false);
        return new PlanDetails(distance, capacity, fare);
    }

    public DirectionsRoute getPlanRoute(String origin, String destination) throws IOException, InterruptedException, ApiException {
        return googleService.getDirections(origin, destination);
    }

    public PlanDetails getUavPlanDetails(String origin, String destination, Double capacity) throws Exception {
        Double distance = googleService.calculateStraightDistance(origin, destination);
        Double fare = fareService.computeFare(distance, capacity, true);
        return new PlanDetails(distance, capacity, fare);
    }
}
