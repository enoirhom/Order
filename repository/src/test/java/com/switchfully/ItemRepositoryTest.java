package com.switchfully;


import com.switchfully.item.Item;
import com.switchfully.item.ItemRepository;
import com.switchfully.item.ItemRepositoryJpa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    void addItem_givenValidItem_thenGetItemByIdReturnsItem() {
        Item item = new Item("Item1", "Item 1 description", 10, 49.99);

        itemRepository.addItem(item);
        Optional<Item> actual = itemRepository.findItemById(item.getId());

        Assertions.assertThat(actual).isPresent().get().isEqualTo(item);
    }

    @Test
    void getAllItems_givenEmptyRepository_thenReturnEmptyList() {
        List<Item> stockItems = itemRepository.getAllItems();

        Assertions.assertThat(stockItems).isEmpty();
    }

    @Test
    void getAllItems_givenPopulatedRepository_thenReturnItems() {
        List<Item> expectedItems = populateStockRepository();

        List<Item> actualItems = itemRepository.getAllItems();

        Assertions.assertThat(expectedItems).containsAll(actualItems);
        Assertions.assertThat(actualItems).containsAll(expectedItems);
    }

    private List<Item> populateStockRepository() {
        List<Item> stockItems = new ArrayList<>();

        Item item1 = new Item("Item1", "Item1 description", 10, 9.99);
        Item item2 = new Item("Item2", "Item1 description", 15, 19.99);
        Item item3 = new Item("Item3", "Item1 description", 20, 29.99);

        stockItems.add(item1);
        stockItems.add(item2);
        stockItems.add(item3);

        itemRepository.addItem(item1);
        itemRepository.addItem(item2);
        itemRepository.addItem(item3);

        return stockItems;
    }

}