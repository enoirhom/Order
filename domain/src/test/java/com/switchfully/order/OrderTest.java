package com.switchfully.order;


import com.switchfully.item.ItemGroup;
import com.switchfully.user.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class OrderTest {

    @Nested
    class OrderCreationTest {
        Customer customer;

        @BeforeEach
        void setUp() {
            customer = Mockito.mock(Customer.class);
        }

        @Test
        void orderCreation_givenEmptyItemGroupList_thenThrowIllegalArgumentException() {
            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new Order(customer, new ArrayList<>()));
        }

    }

    @Nested
    class GetTotalPriceTest {
        Order order;
        List<ItemGroup> itemGroups;

        @BeforeEach
        void setUp() {
            Customer customer = Mockito.mock(Customer.class);
            itemGroups = new ArrayList<>();
            ItemGroup item1 = Mockito.mock(ItemGroup.class);
            Mockito.when(item1.getPrice()).thenReturn(10.00);
            itemGroups.add(item1);
            ItemGroup item2 = Mockito.mock(ItemGroup.class);
            Mockito.when(item2.getPrice()).thenReturn(20.00);
            itemGroups.add(item2);
            order = new Order(customer, itemGroups);
        }

        @Test
        void getTotalPrice_givenOrder_thenReturnPrice() {
            double actual = order.getTotalPrice();

            Assertions.assertThat(actual).isEqualTo(30);
        }

    }

}