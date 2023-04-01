package com.laiex.backend.service;

import com.laiex.backend.db.OrderRepository;
import com.laiex.backend.db.entity.OrderEntity;
import com.laiex.backend.model.responsebody.HistoryOrderBody;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService{
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // user place order, and the order will be inserted into the database, redirect user to the Stripe payment url
    public void placeOrder(long userId, LocalDateTime orderTime, LocalDateTime estimatedPickTime, LocalDateTime estimatedDeliveryTime,
                           String pickupAddr, String deliveryAddr, long carrierId, double price, OrderEntity.OrderStatus status,
                           String stripeProductId) throws InterruptedException {
        orderRepository.insertNewOrder(userId, orderTime, estimatedPickTime, estimatedDeliveryTime, pickupAddr, deliveryAddr, carrierId, price, status,stripeProductId);
        orderRepository.getOrderIdByUserIdAndOrderTime(userId, orderTime);
    }

    // user checks his/her order history
    public List<HistoryOrderBody> getOrderHistoryByUserId(Long userId) {
        List<OrderEntity> orders = orderRepository.findByUserIdNewestToOldest(userId);
        List<HistoryOrderBody> orderReponse = new ArrayList<>();
        for (OrderEntity order : orders) {
            orderReponse.add(new HistoryOrderBody(order.id(), order.orderTime(),
                    order.estimatedPickTime(), order.estimatedDeliveryTime(),
                    order.pickupAddr(), order.deliveryAddr(), order.carrierId(),
                    order.price(), order.status()));
        }
        return orderReponse;
    }

}
