package com.laiex.backend;

import com.laiex.backend.algorithms.FareService;
import com.laiex.backend.db.CarrierRepository;
import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.StationRepository;
import com.laiex.backend.db.UserRepository;
import com.laiex.backend.db.entity.CarrierEntity;
import com.laiex.backend.service.outside.GoogleService;
import com.laiex.backend.service.OrderService;
import com.laiex.backend.service.outside.StripeService;
import com.laiex.backend.service.UserService;
import com.laiex.backend.service.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


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

    private final PasswordEncoder passwordEncoder;

    private final GoogleService googleService;

    private final FareService fareService;

    private final SearchService searchService;

    public DevelopmentTester(UserRepository userRepository, CarrierRepository carrierRepository, StationRepository stationRepository, OrderRepository orderRepository, UserService userService, OrderService orderService, OrderService orderService1, StripeService stripeService, PasswordEncoder passwordEncoder, GoogleService googleService, FareService fareService, SearchService searchService) {
        this.userRepository = userRepository;
        this.carrierRepository = carrierRepository;
        this.stationRepository = stationRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderService = orderService1;
        this.stripeService = stripeService;
        this.passwordEncoder = passwordEncoder;
        this.googleService = googleService;
        this.fareService = fareService;
        this.searchService = searchService;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // UserEntity test
//        UserEntity user1 = new UserEntity(null,"test@gmail.com", "123","john", "z",1231233213);
//        userRepository.save(user1);
//
//        UserEntity user2 = new UserEntity(null,"test2@gmail.com", "123","john", "z",1231233213);
//        userRepository.save(user2);
//
        // CarrierEntity test
//        CarrierEntity carrier1 = new CarrierEntity(null, CarrierEntity.CarrierType.RobotCar, 50.0, 1000.0, Integer.MAX_VALUE);
//        carrierRepository.save(carrier1);

//        CarrierEntity carrier2 = new CarrierEntity(null, CarrierEntity.CarrierType.UAV, 0, 500, Integer.MAX_VALUE);
//        carrierRepository.save(carrier2);

////
//        //StationEntity test
//        StationEntity station1 = new StationEntity(null, "San Francisco", "285 Olympia Way, San Francisco, CA 94131", 37.75112100170498, -122.456254888161, null);
//        StationEntity station2 = new StationEntity(null, "San Francisco", "24 Willie Mays Plaza, San Francisco, CA 94107", 37.77877584407026, -122.3887915467984, null);
//        stationRepository.save(station1);
//        stationRepository.save(station2);

//        // OrderEntity test
//        OrderEntity order1 = new OrderEntity(null, 1L, LocalTime.now(), LocalTime.now(), LocalTime.now(), "285 Olympia Way, San Francisco, CA 94131", "24 Willie Mays Plaza, San Francisco, CA 94107",
//                                                1L, 150.79, OrderEntity.status.ordered);
//        orderRepository.save(order1);

        //register tester
        //userService.register("abc@gmail.com", "123", "john","z","1231233213");


        // stripe product generator test
//        String productId1 = stripeService.createRide(1 + "" + LocalTime.now());
//        System.out.println("Product id is " + productId1);
//        String priceId1 = stripeService.attachPriceToProductId(10549, productId1);
//        System.out.println("Price id is " + priceId1);
//        stripeService.stripeOrderGenerator(productId1, priceId1, 50000);
//        System.out.println("Checkout session is succeed!");
//
//        orderService.placeOrder(1L, LocalDateTime.parse("2023-03-24 10:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                LocalDateTime.parse("2023-03-24 10:30:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                LocalDateTime.parse("2023-03-24 10:30:15",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "1600 Holloway Ave, San Francisco, CA 94132",
//                "450 10th St, San Francisco, CA 94103", 1L, 150.5, OrderEntity.OrderStatus.ordered, productId1);
//
//        String productId2 = stripeService.createRide(1 + "" + LocalTime.now());
//        stripeService.attachPriceToProductId(10549, productId2);
//        stripeService.stripOrderGenerator(productId2);
//        orderService.placeOrder(2L, LocalDateTime.parse("2023-03-24 10:30:05",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                LocalDateTime.parse("2023-03-24 10:30:08",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                LocalDateTime.parse("2023-03-24 10:30:13",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "1600 Holloway Ave, San Francisco, CA 94132", "450 10th St, San Francisco, CA 94103",
//                2L, 150.5, OrderEntity.status.ordered, productId1);

//        String orign = "1517 W 28th St, Los Angeles, CA 90007";
//        String destination = "651 W 35th St, Los Angeles, CA 90089";
//        System.out.println("The distance is " + googleService.calculateDistance(orign,destination));
//        System.out.println("The direction is " + googleService.getDirections(orign, destination).toString());
//
//
//        // calculate straight distance
//        System.out.println("The straight line distance is " + googleService.calculateStraightDistance(orign, destination));
        //test for history
//        List<OrderEntity> re = orderRepository.findByUserIdNewestToOldest(1L);
//        System.out.println("I finished ---------------");
//        String origin = "1517 W 28th St, Los Angeles, CA 90007";
//        String destination = "651 W 35th St, Los Angeles, CA 90089";
//        System.out.println("The distance is " + googleService.calculateDistance(orign,destination));
//        System.out.println("The direction is " + googleService.getDirections(orign, destination).toString());

        // calculate straight distance
//        System.out.println("The straight line distance is " + googleService.calculateStraightDistance(orign, destination));

        // Test FareService
//        System.out.println("The estimated fare is " + fareService.computeFare(1400.0, 100.0, true));
//        System.out.println("The estimated fare is " + fareService.computeFare(1400.0, 100.0, false));
//        System.out.println("The estimated fare is " + fareService.computeFare(14000.0, 100.0, true));
//        System.out.println("The estimated fare is " + fareService.computeFare(1400.0, 400.0, true));
//        System.out.println("The estimated fare is " + fareService.computeFare(1400.0, 1000.0, true));

        // Test SearchService
//        PlanDetails planDetails = searchService.getPlanDetails(origin, destination, 100.0);
//        PlanDetails uavPlanDetails = searchService.getUavPlanDetails(origin, destination, 200.0);
//        System.out.println("The distance for ground carrier is " + planDetails.distance());
//        System.out.println("The capacity for ground carrier is " + planDetails.capacity());
//        System.out.println("The estimated fare for ground carrier is " + planDetails.price());
//        System.out.println("The direction for ground carrier is " + searchService.getPlanRoute(origin, destination).toString());
//
//        System.out.println("The distance for UAV is " + uavPlanDetails.distance());
//        System.out.println("The capacity for UAV is " + uavPlanDetails.capacity());
//        System.out.println("The estimated fare for UAV is " + uavPlanDetails.price());

//        System.out.println("The straight line distance is " + googleService.calculateStraightDistance(orign, destination));
//
//        // Mock Carrier entries
//        CarrierEntity carrier1 = new CarrierEntity(null, "RobotCar", 0, 1000, Integer.MAX_VALUE);
//        carrierRepository.save(carrier1);
//
//        CarrierEntity carrier2 = new CarrierEntity(null, "UAV", 0, 500, Integer.MAX_VALUE);
//        carrierRepository.save(carrier2);


        // RoutePlanning test
//        PlanDetails grond = searchService.getPlanDetails(station, CarrierEntity.CarrierType.RobotCar,"1517 W 28th St, Los Angeles, CA 90007", "651 W 35th St, Los Angeles, CA 90089", 25.0);
//        PlanDetails air = searchService.getPlanDetails(station, CarrierEntity.CarrierType.UAV,"1517 W 28th St, Los Angeles, CA 90007", "651 W 35th St, Los Angeles, CA 90089", 25.0);
//        List<PlanDetails> re = new ArrayList<>();
//        re.add(grond);
//        re.add(air);
//
//        RoutePlanning routePlanning = new RoutePlanning(searchService, "1517 W 28th St, Los Angeles, CA 90007", "651 W 35th St, Los Angeles, CA 90089", 25.0);
//        SearchResponse re1 = routePlanning.getPlanDetails();
//        System.out.println("I'm good");

        // fare test
//        CarrierEntity.CarrierType carrierType1 = CarrierEntity.CarrierType.RobotCar;
//        CarrierEntity.CarrierType carrierType2 = CarrierEntity.CarrierType.UAV;
//        double distance = 16000;
//        double weight = 5;
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 5.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 5.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 10.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 10.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 20.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 20.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 40.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 40.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 60.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 60.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 80.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 80.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 100.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 100.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 120.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 120.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 140.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 140.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 160.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 160.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 180.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 180.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 200.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 200.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 300.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 300.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 400.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 400.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 500.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 500.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 600.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 600.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 700.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 700.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 800.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 800.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 900.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 900.0));
//        System.out.println("The robocar fare is " + fareService.computeFare(carrierType1, distance, 1000.0));
//        System.out.println("The uav fare is " + fareService.computeFare(carrierType2, distance, 1000.0));

    }
}
