package com.laiex.backend.db;

import com.laiex.backend.db.entity.CarrierEntity;
import com.laiex.backend.db.entity.OrderEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {
    @Modifying
    @Query("INSERT INTO orders (user_id, order_time, estimated_pick_time, estimated_delivery_time, pickup_addr, delivery_addr, carrier_id, price, status) " +
            "VALUES (:userId, :orderTime, :estimatedPickTime, :estimatedDeliveryTime, :pickupAddr, :deliveryAddr, :carrierId, :price, :status)")
    void insertNewOrder(Long userId, LocalTime orderTime, LocalTime estimatedPickTime, LocalTime estimatedDeliveryTime, String pickupAddr,
                        String deliveryAddr, Long carrierId, Double price, OrderEntity.status status);

}
