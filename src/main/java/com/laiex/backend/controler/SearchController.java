package com.laiex.backend.controler;

import com.google.maps.errors.ApiException;
import com.laiex.backend.algorithms.RoutePlanning;
import com.laiex.backend.model.requestbody.SearchRequestBody;
import com.laiex.backend.model.responsebody.SearchResponse;
import com.laiex.backend.service.GoogleService;
import com.laiex.backend.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/home")
public class SearchController {
    private final SearchService searchService;
    private final GoogleService googleService;

    public SearchController(SearchService searchService, GoogleService googleService) {
        this.searchService = searchService;
        this.googleService = googleService;
    }

    // get plan result
    @GetMapping("/search")
    public SearchResponse getPlanDetails(@RequestBody SearchRequestBody searchRequestBody) throws IOException, InterruptedException, ApiException {
        //System.out.println("I can get the search request body");
        try {
            RoutePlanning routePlanning = new RoutePlanning(searchService, googleService, searchRequestBody.origin(), searchRequestBody.destination(), searchRequestBody.weight());
            return routePlanning.getPlanDetails();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

//    @GetMapping("/ground")
//    public PlanDetails getGroundPlanDetails(@AuthenticationPrincipal User user, @RequestBody SearchRequestBody searchRequestBody) throws IOException, InterruptedException, ApiException {
//        try {
//            return searchService.getPlanDetails(searchRequestBody.origin(), searchRequestBody.destination(), searchRequestBody.capacity());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//
//    @GetMapping("/uav_direction")
//    public PlanDetails getUavPlanDetails(@AuthenticationPrincipal User user, @RequestBody SearchRequestBody searchRequestBody) throws Exception {
//        try {
//            return searchService.getUavPlanDetails(searchRequestBody.origin(), searchRequestBody.destination(), searchRequestBody.capacity());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//
//    @GetMapping("/car_direction")
//    public DirectionsRoute getGroundRoute(@AuthenticationPrincipal User user, @RequestBody SearchRequestBody searchRequestBody) throws IOException, InterruptedException, ApiException {
//        try {
//            return searchService.getPlanRoute(searchRequestBody.origin(), searchRequestBody.destination());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
}
