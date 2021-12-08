package com.switchfully.item;

public class OrderItem {
    private final Item item;
    private final int quantity;
    private final double price;

    public OrderItem(StockItem stockItem, int quantity) {
        this.item = stockItem.item;
        this.quantity = quantity;
        this.price = stockItem.getPrice();
    }

    public String getId() {
        return item.id;
    }

    public String getName() {
        return item.name;
    }

    public String getDescription() {
        return item.description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
