package com.laiex.backend.db;

import com.laiex.backend.db.entity.CarrierEntity;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends ListCrudRepository<CarrierEntity, Long> {
    @Modifying
    @Query("INSERT INTO carriers (shipping_method, burden, capacity, battery) " +
            "VALUES (:type, :burden, :capacity, :battery)")
    void insertNewCarrier(CarrierEntity.CarrierType type, Double burden, Double capacity, Integer battery);

    @Query("SELECT id FROM carriers ORDER BY id DESC LIMIT 1")
    long getId();
}
