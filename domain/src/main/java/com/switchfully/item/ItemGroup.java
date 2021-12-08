package com.switchfully.item;

import java.time.LocalDate;

public class ItemGroup {
    private static final int NORMAL_SHIPPING_DELAY = 1;
    private static final int OUT_OF_STOCK_SHIPPING_DELAY = 7;

    private final Item item;
    private final int quantity;
    private final double price;
    private final LocalDate shippingDate;

    public ItemGroup(StockItem stockItem, int quantity) {
        this.item = stockItem.item;
        this.quantity = quantity;
        this.price = stockItem.getPrice();
        this.shippingDate = calculateShippingDate(stockItem.getQuantity(), quantity);
    }

    private LocalDate calculateShippingDate(int stockQuantity, int orderQuantity) {
        if (stockQuantity >= orderQuantity) {
            return LocalDate.now().plusDays(NORMAL_SHIPPING_DELAY);
        }
        return LocalDate.now().plusDays(OUT_OF_STOCK_SHIPPING_DELAY);
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
