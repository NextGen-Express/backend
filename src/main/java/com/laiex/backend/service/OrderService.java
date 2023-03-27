package com.laiex.backend.service;

import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.UserRepository;
import com.laiex.backend.db.entity.OrderEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService{
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(long userId, LocalDateTime orderTime, LocalDateTime estimatedPickTime, LocalDateTime estimatedDeliveryTime,
                           String pickupAddr, String deliveryAddr, long carrierId, double price, OrderEntity.status status,
                           String stripeProductId) throws InterruptedException {
        orderRepository.insertNewOrder(userId, orderTime, estimatedPickTime, estimatedDeliveryTime, pickupAddr, deliveryAddr, carrierId, price, status,stripeProductId);
        Long orderId = orderRepository.getOrderIdByUserIdAndOrderTime(userId, orderTime);
        System.out.println(orderId + " has been ordered");

    }

    public List<OrderEntity> getOrderHistoryByUserId(Long userId) {
        return orderRepository.findByUserIdNewestToOldest(userId);
    }


}
