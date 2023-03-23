package com.laiex.backend.controler;

import com.laiex.backend.db.entity.OrderEntity;
import com.laiex.backend.model.BookRequestBody;
import com.laiex.backend.service.OrderService;
import com.laiex.backend.service.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final StripeService stripeService;

    public OrderController(OrderService orderService, StripeService stripeService) {
        this.orderService = orderService;
        this.stripeService = stripeService;
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

        OrderEntity.status status = bookRequestBody.status();

        orderService.placeOrder(userId, orderTime, estimatedPickTime, estimatedDeliveryTime, pickupAddr, deliveryAddr, carrierId, price, status);

//        String priceId = stripeService.StripOrderGenerator();
//        System.out.println("priceid is " + priceId);

    }

}