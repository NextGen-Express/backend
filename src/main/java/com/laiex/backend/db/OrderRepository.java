package com.laiex.backend.db;

import com.laiex.backend.db.entity.OrderEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;

import java.time.LocalTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {
    @Modifying
    @Query("INSERT INTO orders (user_id, order_time, estimated_pick_time, estimated_delivery_time, pickup_addr, delivery_addr, carrier_id, price, status, strpie_product_id) " +
            "VALUES (:userId, :orderTime, :estimatedPickTime, :estimatedDeliveryTime, :pickupAddr, :deliveryAddr, :carrierId, :price, :status, :stripeProductId)")
    void insertNewOrder(Long userId, LocalDateTime orderTime, LocalDateTime estimatedPickTime, LocalDateTime estimatedDeliveryTime, String pickupAddr,
                        String deliveryAddr, Long carrierId, Double price, OrderEntity.OrderStatus status, String stripeProductId);

    @Query("SELECT id FROM orders WHERE user_id = :userId AND order_time = :orderTime")
    Long getOrderIdByUserIdAndOrderTime(Long userId, LocalDateTime orderTime);

    @Modifying
    @Query("UPDATE orders SET status = :newStatus WHERE id = :orderId")
    void updateStatus(Long orderId, Enum<OrderEntity.OrderStatus> newStatus);

    @Query("SELECT * FROM orders WHERE user_id = :userId ORDER BY CASE status " +
            "WHEN 'ordered' THEN 1 WHEN 'pickup' THEN 2 WHEN 'delivered' THEN 3 " +
            "WHEN 'reviewed' THEN 4 ELSE 5 END ASC, order_time DESC")
    List<OrderEntity> findByUserIdNewestToOldest(Long userId);
}

