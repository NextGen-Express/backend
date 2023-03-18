package com.laiex.backend;

import com.laiex.backend.db.CarrierRepository;
import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.UserRepository;
import com.laiex.backend.db.entity.CarrierEntity;
import com.laiex.backend.db.entity.OrderEntity;
import com.laiex.backend.db.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.LocalTime;
import java.util.List;

@Component
public class DevelopmentTester implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DevelopmentTester.class);
    private final UserRepository userRepository;
    private CarrierRepository carrierRepository;

    private OrderRepository orderRepository;

    public DevelopmentTester(UserRepository userRepository,
                             CarrierRepository carrierRepository,
                             OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.carrierRepository = carrierRepository;
        this.orderRepository = orderRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity user1 = new UserEntity(null,"a@gmail.com","123", "a","z",123);
        UserEntity user2 = new UserEntity(null,"b@gmail.com","123", "a","z",123);
        userRepository.save(user1);

        CarrierEntity carrier1 = new CarrierEntity(null, "UAV",null,null,null);
        carrierRepository.save(carrier1);

        OrderEntity orderEntity = new OrderEntity(null, 1L, LocalTime.now(), LocalTime.now(), LocalTime.now(), "home","school", 1L, 130.50, null);

        orderRepository.save(orderEntity);


    }
}
