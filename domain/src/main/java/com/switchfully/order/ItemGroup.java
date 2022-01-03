package com.switchfully.order;

import com.switchfully.item.Item;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {
    private static final int NORMAL_SHIPPING_DELAY = 1;
    private static final int OUT_OF_STOCK_SHIPPING_DELAY = 7;

    private final Item item;
    private final int quantity;
    private final double unitPrice;
    private final LocalDate shippingDate;

    public ItemGroup(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = item.getPrice();
        this.shippingDate = calculateShippingDate(item.getStockQuantity(), quantity);
    }

    private LocalDate calculateShippingDate(int stockQuantity, int orderQuantity) {
        if (stockQuantity >= orderQuantity) {
            return LocalDate.now().plusDays(NORMAL_SHIPPING_DELAY);
        }
        return LocalDate.now().plusDays(OUT_OF_STOCK_SHIPPING_DELAY);
    }

    public UUID getItemId() {
        return item.getId();
    }

    public String getName() {
        return item.getName();
    }

    public String getDescription() {
        return item.getDescription();
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getPrice() {
        return unitPrice * quantity;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
