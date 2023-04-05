package com.laiex.backend.controler;

import com.laiex.backend.algorithms.RoutePlanning;
import com.laiex.backend.model.requestbody.SearchRequestBody;
import com.laiex.backend.model.responsebody.SearchResponse;
import com.laiex.backend.service.outside.GoogleService;
import com.laiex.backend.service.SearchService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/search")
    public SearchResponse getPlanDetails(@RequestBody SearchRequestBody searchRequestBody){
        try {
            RoutePlanning routePlanning = new RoutePlanning(searchService, googleService, searchRequestBody.origin(), searchRequestBody.destination(), searchRequestBody.weight());
            return routePlanning.getPlanDetails();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
