package com.switchfully.item;


import com.switchfully.order.ItemGroup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ItemGroupTest {

    @Test
    void itemGroupCreation_givenValidValuesAndQuantityLessThanStock_thenItemIsCreated() {
        String name = "Item1";
        String description = "Item1 description";
        int stockQuantity = 10;
        double stockPrice = 49.99;
        Item stockItem = new Item(name, description, stockQuantity, stockPrice);

        int quantity = 2;
        ItemGroup actual = new ItemGroup(stockItem, quantity);

        Assertions.assertThat(actual.getName()).isEqualTo(name);
        Assertions.assertThat(actual.getDescription()).isEqualTo(description);
        Assertions.assertThat(actual.getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(actual.getUnitPrice()).isEqualTo(stockPrice);
        Assertions.assertThat(actual.getPrice()).isEqualTo(stockPrice * quantity);
        Assertions.assertThat(actual.getShippingDate()).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void itemGroupCreation_givenValidValuesAndQuantityMoreThanStock_thenItemIsCreated() {
        String name = "Item1";
        String description = "Item1 description";
        int stockQuantity = 10;
        double stockPrice = 49.99;
        Item stockItem = new Item(name, description, stockQuantity, stockPrice);

        int quantity = 15;
        ItemGroup actual = new ItemGroup(stockItem, quantity);

        Assertions.assertThat(actual.getName()).isEqualTo(name);
        Assertions.assertThat(actual.getDescription()).isEqualTo(description);
        Assertions.assertThat(actual.getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(actual.getUnitPrice()).isEqualTo(stockPrice);
        Assertions.assertThat(actual.getPrice()).isEqualTo(stockPrice * quantity);
        Assertions.assertThat(actual.getShippingDate()).isEqualTo(LocalDate.now().plusDays(7));
    }

}