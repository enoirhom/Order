package com.switchfully.item;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void stockItemCreation_givenValidValues_thenItemIsCreated() {
        String name = "Item1";
        String description = "Item1 description";
        int quantity = 10;
        double price = 49.99;

        Item actual = new Item(name, description, quantity, price);

        Assertions.assertThat(actual.getName()).isEqualTo(name);
        Assertions.assertThat(actual.getDescription()).isEqualTo(description);
        Assertions.assertThat(actual.getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(actual.getPrice()).isEqualTo(price);
    }

    @Test
    void removeQuantityFromStock_givenQuantity_thenQuantityIsRemoved() {
        Item stockItem = new Item("Item1", "Item1 description", 10, 49.99);

        stockItem.removeQuantityFromStock(5);

        Assertions.assertThat(stockItem.getQuantity()).isEqualTo(5);
    }

    @Test
    void removeQuantityFromStock_givenBiggerQuantityThanStock_thenStockEqualsZero() {
        Item stockItem = new Item("Item1", "Item1 description", 10, 49.99);

        stockItem.removeQuantityFromStock(15);

        Assertions.assertThat(stockItem.getQuantity()).isEqualTo(0);
    }

}