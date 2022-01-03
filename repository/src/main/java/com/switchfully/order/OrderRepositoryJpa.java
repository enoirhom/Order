package com.switchfully.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Primary
public class OrderRepositoryJpa implements OrderRepository {
    private final Map<String, Order> orders;

    @Autowired
    public OrderRepositoryJpa() {
        this.orders = new ConcurrentHashMap<>();
    }

    @Override
    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }
}
