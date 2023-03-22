package com.laiex.backend.service;

import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.UserRepository;
import com.laiex.backend.db.entity.OrderEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class BookService {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public BookService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, UserRepository userRepository, OrderRepository orderRepository) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }


    public void placeOrder(Long userId, LocalTime orderTime, LocalTime estimatedPickTime, LocalTime estimatedDeliveryTime, String pickupAddr, String deliveryAddr, Long carrierId, Double price, OrderEntity.status status) {
        orderRepository.insertNewOrder(userId, orderTime, estimatedPickTime, estimatedDeliveryTime, pickupAddr,deliveryAddr,carrierId, price, status);
    }
}
