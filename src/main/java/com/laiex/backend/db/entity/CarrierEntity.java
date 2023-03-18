package com.laiex.backend.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Table("carriers")
public record CarrierEntity(
        @Id Long id,
        String type,
        @JsonProperty("weight_capacity") String weightCapacity,
        @JsonProperty("max_weight_capacity") String maxWeightCapacity,
        @JsonProperty("battery_level") Double batteryLevel
) {

    // get user id
    public long getId() {
        return id;
    }
   public static List<CarrierEntity> carrierGenerator(int n) {
       String[] types = {"RoboCar", "UAV"};
       List<CarrierEntity> list = new ArrayList<>();
       for(int i = 0; i < n; i++) {
           list.add(new CarrierEntity(null, types[(int) (Math.random() * 2)],null,null, null));
       }
       return list;
   }
}
