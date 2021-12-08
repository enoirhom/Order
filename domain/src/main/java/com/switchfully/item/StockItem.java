package com.switchfully.item;

public class StockItem {
    final Item item;
    private int quantity;
    private double price;

    public StockItem(String name, String description, int quantity, double price) {
        this.item = new Item(name, description);
        this.quantity = quantity;
        this.price = price;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
