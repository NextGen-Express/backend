package com.laiex.backend.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class StripeService {
    public String stripOrderGenerator(String productId) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("unit_amount", 2000);
        params.put("currency", "usd");
        params.put("product", productId);

        // Create the price
        try {
            Price price = Price.create(params);
            return price.getId();
        } catch (StripeException e) {
            System.out.println(e.getStripeError());
            return "";
        }


        // Retrieve the price ID

    }

    public String createRide(String rideName) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Gold Special");
        Product product = null;
        try{
            product = Product.create(params);
        } catch (Exception e) {
            System.out.println("Your ride was not successfully!");
        }
        return product.getId();

    }


    public void attachPriceToProductId(double cost, String productId) {
        Map<String, Object> recurring = new HashMap<>();
        recurring.put("interval", "month");
        Map<String, Object> params = new HashMap<>();
        params.put("unit_amount", (int) (cost * 100));
        params.put("currency", "usd");
        params.put("recurring", recurring);
        params.put("product", "prod_NVYNC0xmxzmB2Y");
        try{
            Price price = Price.create(params);
        } catch(Exception e) {
            System.out.println("Price was not successfully attached to " + productId);
        }

    }
}
