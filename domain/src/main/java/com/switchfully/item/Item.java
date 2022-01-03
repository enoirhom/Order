package com.switchfully.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "price")
    private double price;

    public Item(String name, String description, int stockQuantity, double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    protected Item() {

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void removeQuantityFromStock(int quantity) {
        this.stockQuantity -= quantity;
        if (this.stockQuantity < 0) {
            this.stockQuantity = 0;
        }
    }
}
