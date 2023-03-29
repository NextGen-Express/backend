package com.laiex.backend;

import com.laiex.backend.db.CarrierRepository;
import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.StationRepository;
import com.laiex.backend.db.UserRepository;
import com.laiex.backend.db.entity.CarrierEntity;
import com.laiex.backend.db.entity.OrderEntity;
import com.laiex.backend.service.OrderService;
import com.laiex.backend.service.StripeService;
import com.laiex.backend.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@Component
public class DevelopmentTester implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DevelopmentTester.class);
    private final UserRepository userRepository;
    private final CarrierRepository carrierRepository;
    private final StationRepository stationRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderService orderService;
    private final StripeService stripeService;

    public DevelopmentTester(UserRepository userRepository, CarrierRepository carrierRepository, StationRepository stationRepository, OrderRepository orderRepository, UserService userService, OrderService orderService, OrderService orderService1, StripeService stripeService) {
        this.userRepository = userRepository;
        this.carrierRepository = carrierRepository;
        this.stationRepository = stationRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderService = orderService1;
        this.stripeService = stripeService;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        // UserEntity test
//        UserEntity user1 = new UserEntity(null,"test@gmail.com", "123","john", "z",1231233213);
//        userRepository.save(user1);
//
//        UserEntity user2 = new UserEntity(null,"test2@gmail.com", "123","john", "z",1231233213);
//        userRepository.save(user2);
//
        // CarrierEntity test
        CarrierEntity carrier1 = new CarrierEntity(null, CarrierEntity.CarrierType.RobotCar, 0, 1000, Integer.MAX_VALUE);
        carrierRepository.save(carrier1);
//
//        CarrierEntity carrier2 = new CarrierEntity(null, CarrierEntity.CarrierType.UAV, 0, 500, Integer.MAX_VALUE);
//        carrierRepository.save(carrier2);
//
//        //StationEntity test
//        StationEntity station1 = new StationEntity(null, "San Francisco", "285 Olympia Way, San Francisco, CA 94131", 37.75112100170498, -122.456254888161, null);
//        StationEntity station2 = new StationEntity(null, "San Francisco", "24 Willie Mays Plaza, San Francisco, CA 94107", 37.77877584407026, -122.3887915467984, null);
//        stationRepository.save(station1);
//        stationRepository.save(station2);
//
//        // OrderEntity test
//        OrderEntity order1 = new OrderEntity(null, 1L, LocalTime.now(), LocalTime.now(), LocalTime.now(), "285 Olympia Way, San Francisco, CA 94131", "24 Willie Mays Plaza, San Francisco, CA 94107",
//                                                1L, 150.79, OrderEntity.status.ordered);
//        orderRepository.save(order1);

        //register tester
        userService.register("abc@gmail.com", "123", "john","z",1231233213);


        // stripe product generator test
        String productId = stripeService.createRide();
        stripeService.attachPriceToProductId(105.49, productId);
        stripeService.stripOrderGenerator(productId);

        // book tester
        orderService.placeOrder(1L, LocalTime.parse("10:30:15.500"), LocalTime.parse("10:30:15.500"), LocalTime.parse("10:30:15.500"),
                "1600 Holloway Ave, San Francisco, CA 94132", "450 10th St, San Francisco, CA 94103",
                1L, 150.5, OrderEntity.status.ordered);



<<<<<<< Updated upstream
=======
        // calculate straight distance
        System.out.println("The straight line distance is " + googleService.calculateStraightDistance(orign, destination));

        // Mock Carrier entries
        CarrierEntity carrier1 = new CarrierEntity(null, "RobotCar", 0, 1000, Integer.MAX_VALUE);
        carrierRepository.save(carrier1);

        CarrierEntity carrier2 = new CarrierEntity(null, "UAV", 0, 500, Integer.MAX_VALUE);
        carrierRepository.save(carrier2);


>>>>>>> Stashed changes
    }
}
