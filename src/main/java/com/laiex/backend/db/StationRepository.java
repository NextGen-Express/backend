package com.laiex.backend.db;

import com.laiex.backend.db.entity.StationEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface StationRepository extends ListCrudRepository<StationEntity, Long> {

    @Modifying
    @Query("INSERT INTO stations (city, address, latitude, longitude, status) VALUES (:city, :address, :latitude, :longitude, :status)")
    void insertStation(String city, String address, double latitude, double longitude, StationEntity.status status);
}
