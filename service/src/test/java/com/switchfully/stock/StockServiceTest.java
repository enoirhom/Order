package com.switchfully.stock;


import com.switchfully.StockRepository;
import com.switchfully.customer.dto.CustomerDto;
import com.switchfully.stock.dto.CreateStockItemDto;
import com.switchfully.stock.dto.StockItemDto;
import com.switchfully.stock.dto.StockItemDtoMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class StockServiceTest {
    StockService stockService;

    @BeforeEach
    void setup() {
        stockService = new StockService(new StockRepository(), new StockItemDtoMapper());
    }

    @Test
    void addItem_givenCreateStockItemDto_thenItemIsAdded() {
        CreateStockItemDto createStockItemDto = new CreateStockItemDto("Item1", "Item1 description", 10, 49.99);

        StockItemDto expected = stockService.addStockItem(createStockItemDto);
        StockItemDto actual = stockService.getStockItemById(expected.id());

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllStockItems_thenReturnsAllItemsDto() {
        List<StockItemDto> expected = add3Items();

        List<StockItemDto> actual = stockService.getAllItems();

        actual.forEach(stockItemDto -> Assertions.assertThat(stockItemDto).isIn(expected));
    }

    private List<StockItemDto> add3Items() {
        List<StockItemDto> stockItemDtos = new ArrayList<>();

        stockItemDtos.add(stockService.addStockItem(new CreateStockItemDto("Item1", "Item1 description", 10, 49.99)));
        stockItemDtos.add(stockService.addStockItem(new CreateStockItemDto("Item2", "Item2 description", 20, 149.99)));
        stockItemDtos.add(stockService.addStockItem(new CreateStockItemDto("Item3", "Item3 description", 30, 249.99)));

        return stockItemDtos;
    }

}