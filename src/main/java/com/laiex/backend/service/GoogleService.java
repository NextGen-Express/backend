package com.laiex.backend.service;

import com.google.maps.*;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleService {
    private final GeoApiContext geoApiContext;

    public GoogleService(GeoApiContext geoApiContext) {
        this. geoApiContext = geoApiContext;
    }

    // calculate distance between two addresses
    public double calculateDistance(String origin, String destination) throws IOException, InterruptedException, ApiException {
        String[] origins = new String[]{origin};
        String[] destinations = new String[]{destination};
        DistanceMatrixApiRequest distanceMatrixApiRequest
                = DistanceMatrixApi.getDistanceMatrix(geoApiContext, origins, destinations)
                .mode(TravelMode.DRIVING)
                .language("en-US")
                .units(Unit.METRIC);
        DistanceMatrix matrix = distanceMatrixApiRequest.await();
        DistanceMatrixElement[] elements = matrix.rows[0].elements;
        Distance distance = elements[0].distance;

        return distance.inMeters;
    }

    public DirectionsRoute getDirections(String origin, String destination) throws IOException, InterruptedException, ApiException {
        DirectionsApiRequest request = DirectionsApi.newRequest(geoApiContext)
                .origin(origin)
                .destination(destination)
                .mode(TravelMode.DRIVING);
        return request.await().routes[0];
    }

    // get straight-line distance
    public double calculateStraightDistance(String origin, String destination) throws Exception {
        LatLng originLatLng = getLatLng(origin);
        LatLng destinationLatLng = getLatLng(destination);
        int R = 6371000; // Earth radius in meters
        double lat1 = originLatLng.lat * Math.PI / 180;
        double lat2 = destinationLatLng.lat * Math.PI / 180;
        double deltaLat = (destinationLatLng.lat - originLatLng.lat) * Math.PI / 180;
        double deltaLng = (destinationLatLng.lng - originLatLng.lng) * Math.PI / 180;

        double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(deltaLng/2) * Math.sin(deltaLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }

    // get straight line of two addresses - this can only be done by frontend


    private LatLng getLatLng(String addr) throws Exception {
        GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, addr).await();
        if (results == null || results.length == 0) {
            throw new Exception("Unable to geocode address: " + addr);
        }
        return results[0].geometry.location;
    }

}
