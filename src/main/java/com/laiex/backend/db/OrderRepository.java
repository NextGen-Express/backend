package com.laiex.backend.db;

import com.laiex.backend.db.entity.OrderEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.time.LocalTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {
    
    @Modifying
    @Query("INSERT INTO orders (user_id, order_time, estimated_pick_time, estimated_delivery_time, pickup_addr, delivery_addr, carrier_id, price, status, strpie_product_id) " +
            "VALUES (:userId, :orderTime, :estimatedPickTime, :estimatedDeliveryTime, :pickupAddr, :deliveryAddr, :carrierId, :price, :status, :stripeProductId)")
    void insertNewOrder(Long userId, LocalDateTime orderTime, LocalDateTime estimatedPickTime, LocalDateTime estimatedDeliveryTime, String pickupAddr,
                        String deliveryAddr, Long carrierId, Double price, OrderEntity.Status status, String stripeProductId);

    @Query("SELECT id FROM orders WHERE user_id = :userId AND order_time = :orderTime")
    Long getOrderIdByUserIdAndOrderTime(Long userId, LocalDateTime orderTime);

    @Modifying
    @Query("UPDATE orders SET status = :newStatus WHERE id = :orderId")
    void updateStatus(Long orderId, OrderEntity.Status newStatus);

    @Query("SELECT * FROM orders WHERE user_id = :userId ORDER BY order_time DESC")
    List<OrderEntity> findByUserIdOrderByOrderTimeDesc(Long userId);

    @Query("SELECT * FROM orders\n" +
            "WHERE user_id = :userId\n" +
            "ORDER BY\n" +
            "  CASE status\n" +
            "    WHEN 'ordered' THEN 1\n" +
            "    WHEN 'pickup' THEN 2\n" +
            "    WHEN 'delivered' THEN 3\n" +
            "    WHEN 'reviewed' THEN 4\n" +
            "    ELSE 5\n" +
            "  END ASC, order_time DESC\n")
    List<OrderEntity> findByUserIdNewestToOldest(Long userId);
}
