package com.switchfully.item;

import java.util.UUID;

public class Item {
    private String id;
    private String name;
    private String description;
    private int quantity;
    private double price;

    public Item(String name, String description, int quantity, double price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void removeQuantityFromStock(int quantity) {
        this.quantity -= quantity;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
    }
}
