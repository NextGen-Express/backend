package com.laiex.backend.db;

import com.laiex.backend.db.entity.OrderEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {
    @Modifying
    @Query("INSERT INTO orders (user_id, order_time, estimated_pick_time, estimated_delivery_time, pickup_addr, delivery_addr, carrier_id, price, status, strpie_product_id) " +
            "VALUES (:userId, :orderTime, :estimatedPickTime, :estimatedDeliveryTime, :pickupAddr, :deliveryAddr, :carrierId, :price, :status, :stripeProductId)")
    void insertNewOrder(Long userId, LocalDateTime orderTime, LocalDateTime estimatedPickTime, LocalDateTime estimatedDeliveryTime, String pickupAddr,
                        String deliveryAddr, Long carrierId, Double price, OrderEntity.status status, String stripeProductId);

    @Query("SELECT id FROM orders WHERE user_id = :userId AND order_time = :orderTime")
    Long getOrderIdByUserIdAndOrderTime(Long userId, LocalDateTime orderTime);


    @Modifying
    @Query("UPDATE orders SET status = :newStatus WHERE id = :orderId")
    void updateStatus(Long orderId, Enum<OrderEntity.status> newStatus);
}
