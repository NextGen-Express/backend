package com.laiex.backend.controler;

import com.laiex.backend.db.entity.OrderEntity;
import com.laiex.backend.model.BookRequestBody;
import com.laiex.backend.service.OrderService;
import com.laiex.backend.service.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
// newly added unsure
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final StripeService stripeService;
    private final UserService userService;

    public OrderController(OrderService orderService, StripeService stripeService, UserService userService) {
        this.orderService = orderService;
        this.stripeService = stripeService;
        this.userService = userService;
    }
    

    @PostMapping("/book")
    @ResponseStatus(value = HttpStatus.OK)
    public void book(@AuthenticationPrincipal User user, @RequestBody BookRequestBody bookRequestBody) throws StripeException {
        // can we get userid directly from frontend? If not, I need to look up userid based on user.getUserName() and look up from db
        Long userId = bookRequestBody.userId();

        LocalTime orderTime = bookRequestBody.orderTime();

        LocalTime estimatedPickTime = bookRequestBody.estimatedPickTime();

        LocalTime estimatedDeliveryTime = bookRequestBody.estimatedDeliveryTime();

        String pickupAddr = bookRequestBody.pickupAddr();

        String deliveryAddr = bookRequestBody.deliveryAddr();

        Long carrierId = bookRequestBody.carrierId();

        Double price = bookRequestBody.price();

<<<<<<< Updated upstream
        OrderEntity.status status = bookRequestBody.status();
=======
        OrderEntity.Status status = OrderEntity.Status.ordered;
>>>>>>> Stashed changes

        orderService.placeOrder(userId, orderTime, estimatedPickTime, estimatedDeliveryTime, pickupAddr, deliveryAddr, carrierId, price, status);

//        String priceId = stripeService.StripOrderGenerator();
//        System.out.println("priceid is " + priceId);

    }

    // newly added need comments
    @GetMapping("/history")
    public List<OrderEntity> getOrderHistory(@AuthenticationPrincipal User user) {
        Long userId = userService.findUserIdByUsername(user.getUsername());
        return orderService.getOrderHistory(userId);
<<<<<<< Updated upstream
    }

=======
>>>>>>> Stashed changes
    }

    public ResponseEntity<List<OrderEntity>> getSortedOrders(@PathVariable Long userId) {
        List<OrderEntity> sortedOrders = orderService.getSortedOrders(userId);
        return ResponseEntity.ok(sortedOrders);
    }
}
