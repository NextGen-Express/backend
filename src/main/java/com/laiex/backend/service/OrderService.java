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
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService{
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //newly added unsure
    public List<OrderEntity> getOrderHistory(Long userId) {
        // return orderRepository.findByUserId(userId);
        return orderRepository.findByUserIdNewestToOldest(userId);
    }  

    public void placeOrder(long userId, LocalDateTime orderTime, LocalDateTime estimatedPickTime, LocalDateTime estimatedDeliveryTime,
                           String pickupAddr, String deliveryAddr, long carrierId, double price, OrderEntity.OrderStatus status,
                           String stripeProductId) throws InterruptedException {
        orderRepository.insertNewOrder(userId, orderTime, estimatedPickTime, estimatedDeliveryTime, pickupAddr, deliveryAddr, carrierId, price, status,stripeProductId);
        Long orderId = orderRepository.getOrderIdByUserIdAndOrderTime(userId, orderTime);
        System.out.println(orderId + " has been ordered");

    }

    public List<OrderEntity> getOrderHistoryByUserId(Long userId) {
        return orderRepository.findByUserIdNewestToOldest(userId);
    }


    }

    public List<OrderEntity> getOrderHistory(Long userId) {
        return orderRepository.findByUserIdOrderByOrderTimeDesc(userId);
    }

    public List<OrderEntity> getSortedOrders(Long userId) {
        List<OrderEntity> orders = orderRepository.findByUserIdOrderByOrderTimeDesc(userId);
        return orders.stream()
                .sorted(Comparator.comparing(OrderEntity::status)
                        .thenComparing(OrderEntity::orderTime).reversed())
                .collect(Collectors.toList());
    }
}
