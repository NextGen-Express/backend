package com.laiex.backend.controler;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsRoute;
import com.laiex.backend.model.PlanDetails;
import com.laiex.backend.model.SearchRequestBody;
import com.laiex.backend.service.SearchService;
import com.stripe.model.Plan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/ground")
    public PlanDetails getGroundPlanDetails(@AuthenticationPrincipal User user, @RequestBody SearchRequestBody searchRequestBody) throws IOException, InterruptedException, ApiException {
        try {
            return searchService.getPlanDetails(searchRequestBody.origin(), searchRequestBody.destination(), searchRequestBody.capacity());
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/uav")
    public PlanDetails getUavPlanDetails(@AuthenticationPrincipal User user, @RequestBody SearchRequestBody searchRequestBody) throws Exception {
        try {
            return searchService.getUavPlanDetails(searchRequestBody.origin(), searchRequestBody.destination(), searchRequestBody.capacity());
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/direction")
    public DirectionsRoute getGroundRoute(@AuthenticationPrincipal User user, @RequestBody SearchRequestBody searchRequestBody) throws IOException, InterruptedException, ApiException {
        try {
            return searchService.getPlanRoute(searchRequestBody.origin(), searchRequestBody.destination());
        } catch (Exception e) {
            throw e;
        }
    }
}
