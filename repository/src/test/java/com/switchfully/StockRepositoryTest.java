package com.switchfully;


import com.switchfully.item.StockItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}