package com.laiex.backend.db.entity;
import com.laiex.backend.config.AppConfig;
import com.laiex.backend.service.outside.GoogleService;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("stations")
public record StationEntity(
        @Id Long id,
        String city,
        String address,
        Double latitude,
        Double longitude,
        String status
) {
    public static StationEntity getClosestStation(GoogleService googleService, String origin) throws Exception {

        StationEntity target = null;
        double distance = Double.MAX_VALUE;
        List<StationEntity> stations = AppConfig.getStationList();
        for(StationEntity station : stations) {
            double[] tempDistanceAndTime = googleService.calculateUAVDistance(station.address, origin);
            if(tempDistanceAndTime[0] < distance) {
                target = station;
                distance = tempDistanceAndTime[0];
            }
        }
        return target;
    }

    public enum status { available, unavailable};

}

