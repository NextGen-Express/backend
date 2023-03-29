package com.laiex.backend.service;

import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.UserRepository;
import com.laiex.backend.db.entity.OrderEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
<<<<<<< Updated upstream
=======
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.Comparator;
>>>>>>> Stashed changes

@Service
public class OrderService {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, UserRepository userRepository, OrderRepository orderRepository) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

<<<<<<< Updated upstream
    //newly added unsure
    public List<OrderEntity> getOrderHistory(Long userId) {
        // return orderRepository.findByUserId(userId);
        return orderRepository.findByUserIdNewestToOldest(userId);
    }  
=======
    public void placeOrder(long userId, LocalDateTime orderTime, LocalDateTime estimatedPickTime, LocalDateTime estimatedDeliveryTime,
                           String pickupAddr, String deliveryAddr, long carrierId, double price, OrderEntity.Status status,
                           String stripeProductId) throws InterruptedException {
        orderRepository.insertNewOrder(userId, orderTime, estimatedPickTime, estimatedDeliveryTime, pickupAddr, deliveryAddr, carrierId, price, status,stripeProductId);
        Long orderId = orderRepository.getOrderIdByUserIdAndOrderTime(userId, orderTime);
        System.out.println(orderId + " has been ordered");

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
>>>>>>> Stashed changes
}
