package com.switchfully.stock;


import com.switchfully.stub.StockRepositoryStub;
import com.switchfully.stock.dto.CreateItemDto;
import com.switchfully.stock.dto.ItemDto;
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
        stockService = new StockService(new StockRepositoryStub(), new StockItemDtoMapper());
    }

    @Test
    void addItem_givenCreateStockItemDto_thenItemIsAdded() {
        CreateItemDto createItemDto = new CreateItemDto("Item1", "Item1 description", 10, 49.99);

        ItemDto expected = stockService.addStockItem(createItemDto);
        ItemDto actual = stockService.getStockDtoItemById(expected.id());

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllStockItems_thenReturnsAllItemsDto() {
        List<ItemDto> expected = add3Items();

        List<ItemDto> actual = stockService.getAllItems();

        actual.forEach(itemDto -> Assertions.assertThat(itemDto).isIn(expected));
    }

    private List<ItemDto> add3Items() {
        List<ItemDto> itemDtos = new ArrayList<>();

        itemDtos.add(stockService.addStockItem(new CreateItemDto("Item1", "Item1 description", 10, 49.99)));
        itemDtos.add(stockService.addStockItem(new CreateItemDto("Item2", "Item2 description", 20, 149.99)));
        itemDtos.add(stockService.addStockItem(new CreateItemDto("Item3", "Item3 description", 30, 249.99)));

        return itemDtos;
    }

}