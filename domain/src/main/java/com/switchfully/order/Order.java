package com.switchfully.order;

import com.switchfully.item.ItemGroup;
import com.switchfully.user.Customer;

import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final Customer customer;
    private final List<ItemGroup> itemGroups;

    public Order(Customer customer, List<ItemGroup> itemGroups) {
        if (itemGroups.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.itemGroups = itemGroups;
    }

    public double getTotalPrice() {
        return itemGroups.stream()
                .map(ItemGroup::getPrice)
                .reduce(0.0, Double::sum);
    }

    public String getId() {
        return id;
    }

}
