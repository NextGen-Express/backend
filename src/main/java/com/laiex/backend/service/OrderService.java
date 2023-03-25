package com.laiex.backend.service;

import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.UserRepository;
import com.laiex.backend.db.entity.OrderEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

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

    //newly added unsure
    public List<OrderEntity> getOrderHistory(Long userId) {
        // return orderRepository.findByUserId(userId);
        return orderRepository.findByUserIdNewestToOldest(userId);
    }  
}
