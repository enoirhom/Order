package com.switchfully;


import com.switchfully.item.Item;
import com.switchfully.item.StockRepository;
import com.switchfully.item.StockRepositoryJpa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class StockRepositoryTest {
    StockRepository stockRepository;

    @BeforeEach
    void setup() {
        stockRepository = new StockRepositoryJpa();
    }

    @Test
    void addItem_givenValidItem_thenGetItemByIdReturnsItem() {
        Item stockItem = new Item("Item1", "Item 1 description", 10, 49.99);

        stockRepository.addItem(stockItem);
        Item actual = stockRepository.getItemById(stockItem.getId());

        Assertions.assertThat(actual).isEqualTo(stockItem);
    }

    @Test
    void getAllItems_givenEmptyRepository_thenReturnEmptyList() {
        List<Item> stockItems = stockRepository.getAllItems();

        Assertions.assertThat(stockItems).isEmpty();
    }

    @Test
    void getAllItems_givenPopulatedRepository_thenReturnItems() {
        List<Item> expectedItems = populateStockRepository();

        List<Item> actualItems = stockRepository.getAllItems();

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

        stockRepository.addItem(item1);
        stockRepository.addItem(item2);
        stockRepository.addItem(item3);

        return stockItems;
    }

}