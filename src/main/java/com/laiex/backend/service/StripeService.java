package com.laiex.backend.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StripeService {
    public String stripeOrderGenerator(String productId, String priceId, Integer price) throws StripeException {
        List<Object> lineItems = new ArrayList<>();
        Map<String, Object> lineItem = new HashMap<>();
        lineItem.put("price", priceId);
        lineItem.put("quantity", 1);

        Map<String, Object> params = new HashMap<>();
        lineItems.add(lineItem);
        params.put("line_items", lineItems);
        params.put("mode", "payment");
        params.put("currency", "usd");
        params.put("success_url","https://example.com/success");
        Session session = Session.create(params);
        System.out.println(session.getUrl());
        return session.getUrl();

    }

    public String createRide(String rideName) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", rideName);
        Product product = null;
        try{
            product = Product.create(params);
        } catch (Exception e) {
            System.out.println("Your ride was not successfully!");
        }
        return product.getId();

    }


    public String attachPriceToProductId(int cost, String productId) {
        Map<String, Object> params = new HashMap<>();
        params.put("unit_amount", cost);
        params.put("currency", "usd");
        params.put("product", productId);
        try{
            Price price = Price.create(params);
            return price.getId();
        } catch(Exception e) {
            System.out.println("Price was not successfully attached to " + productId);
            return null;
        }
    }
}
