package com.laiex.backend.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.entity.OrderEntity;
import com.laiex.backend.model.BookRequestBody;
import com.laiex.backend.service.BookService;
import com.laiex.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.time.LocalTime;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    @ResponseStatus(value = HttpStatus.OK)
    public void book(@AuthenticationPrincipal User user, @RequestBody BookRequestBody bookRequestBody) {
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

        bookService.placeOrder(userId, orderTime, estimatedPickTime, estimatedDeliveryTime, pickupAddr, deliveryAddr, carrierId, price, status);


    }
}
