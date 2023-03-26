package com.laiex.backend.controler;

import com.laiex.backend.db.entity.OrderEntity;
import com.laiex.backend.model.BookRequestBody;
import com.laiex.backend.service.OrderService;
import com.laiex.backend.service.StripeService;
import com.laiex.backend.service.UserService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/home")
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
    public RedirectView book(@AuthenticationPrincipal User user, @RequestBody BookRequestBody bookRequestBody) throws StripeException, InterruptedException {
        System.out.println("I'm booking new order");
        // can we get userid directly from frontend? If not, I need to look up userid based on user.getUserName() and look up from db
        Long userId = userService.findUserIdByUsername(user.getUsername());
        System.out.println("user id is " + userId);
        LocalDateTime orderTime = bookRequestBody.orderTime();

        LocalDateTime estimatedPickTime = bookRequestBody.estimatedPickTime();

        LocalDateTime estimatedDeliveryTime = bookRequestBody.estimatedDeliveryTime();

        String pickupAddr = bookRequestBody.pickupAddr();

        String deliveryAddr = bookRequestBody.deliveryAddr();

        Long carrierId = bookRequestBody.carrierId();

        Double price = bookRequestBody.price();

        OrderEntity.status status = OrderEntity.status.ordered;

        // creat productId on Stripe
        String stripeProductId = stripeService.createRide(userId + "" + orderTime.toString());
        // attach price to above ride
        String stripePriceId = stripeService.attachPriceToProductId((int) (price * 100), stripeProductId);
        // store order to mySQL
        orderService.placeOrder(userId, orderTime, estimatedPickTime, estimatedDeliveryTime, pickupAddr, deliveryAddr, carrierId, price, status, stripeProductId);
        // stripe checkout session
        String redirectUrl = stripeService.stripOrderGenerator(stripeProductId, stripePriceId, (int)(price * 100));

        return new RedirectView(redirectUrl);

    }

    @GetMapping("/history")
    public List<OrderEntity> getOrderHistory(@AuthenticationPrincipal User user) {

        Long userId = userService.findUserIdByUsername(user.getUsername());
        return orderService.getOrderHistoryByUserId(userId);
    }

}
