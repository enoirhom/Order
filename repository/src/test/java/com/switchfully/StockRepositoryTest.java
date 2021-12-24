package com.switchfully;


import com.switchfully.item.StockItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class StockRepositoryTest {
    StockRepository stockRepository;

    @BeforeEach
    void setup() {
        stockRepository = new StockRepository();
    }

    @Test
    void addItem_givenValidStockItem_thenGetItemByIdReturnsStockItem() {
        StockItem stockItem = new StockItem("Item1", "Item 1 description", 10, 49.99);

        stockRepository.addItem(stockItem);
        StockItem actual = stockRepository.getItemById(stockItem.getId());

        Assertions.assertThat(actual).isEqualTo(stockItem);
    }

    @Test
    void getAllItems_givenEmptyRepository_thenReturnEmptyList() {
        List<StockItem> stockItems = stockRepository.getAllItems();

        Assertions.assertThat(stockItems).isEmpty();
    }

    @Test
    void getAllItems_givenPopulatedRepository_thenReturnItems() {
        List<StockItem> expectedStockItems = populateStockRepository();

        List<StockItem> actualStockItems = stockRepository.getAllItems();

        Assertions.assertThat(expectedStockItems).containsAll(actualStockItems);
        Assertions.assertThat(actualStockItems).containsAll(expectedStockItems);
    }

    private List<StockItem> populateStockRepository() {
        List<StockItem> stockItems = new ArrayList<>();

        StockItem item1 = new StockItem("Item1", "Item1 description", 10, 9.99);
        StockItem item2 = new StockItem("Item2", "Item1 description", 15, 19.99);
        StockItem item3 = new StockItem("Item3", "Item1 description", 20, 29.99);

        stockItems.add(item1);
        stockItems.add(item2);
        stockItems.add(item3);

        stockRepository.addItem(item1);
        stockRepository.addItem(item2);
        stockRepository.addItem(item3);

        return stockItems;
    }

}