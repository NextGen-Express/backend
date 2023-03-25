package com.laiex.backend.db;

import com.laiex.backend.db.entity.OrderEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {
    
    @Modifying
    @Query("INSERT INTO orders (user_id, order_time, estimated_pick_time, estimated_delivery_time, pickup_addr, delivery_addr, carrier_id, price, status) " +
            "VALUES (:userId, :orderTime, :estimatedPickTime, :estimatedDeliveryTime, :pickupAddr, :deliveryAddr, :carrierId, :price, :status)")
    void insertNewOrder(Long userId, LocalTime orderTime, LocalTime estimatedPickTime, LocalTime estimatedDeliveryTime, String pickupAddr,
                        String deliveryAddr, Long carrierId, Double price, OrderEntity.status status);

    @Query("SELECT * FROM orders\n" +
            "WHERE user_id = :userId\n" +
            "ORDER BY \n" +
            "  CASE status\n" +
            "    WHEN 'ordered' THEN 1\n" +
            "    WHEN 'pickup' THEN 2\n" +
            "    WHEN 'delivered' THEN 3\n" +
            "    WHEN 'reviewed' THEN 4\n" +
            "    ELSE 5\n" +
            "  END ASC, order_time DESC\n")
    List<OrderEntity> findByUserIdNewestToOldest(Long userId);
}
