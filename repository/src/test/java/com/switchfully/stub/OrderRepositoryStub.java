package com.switchfully.stub;

import com.switchfully.order.Order;
import com.switchfully.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepositoryStub implements OrderRepository {
    private final Map<String, Order> orders;

    @Autowired
    public OrderRepositoryStub() {
        this.orders = new ConcurrentHashMap<>();
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }
}
