package com.switchfully;

import com.switchfully.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {
    private final Map<String, Order> orders;

    @Autowired
    public OrderRepository() {
        this.orders = new ConcurrentHashMap<>();
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }
}
