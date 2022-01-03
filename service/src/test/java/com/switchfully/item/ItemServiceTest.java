package com.switchfully.item;


import com.switchfully.stub.ItemRepositoryStub;
import com.switchfully.item.dto.CreateItemDto;
import com.switchfully.item.dto.ItemDto;
import com.switchfully.item.dto.StockItemDtoMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ItemServiceTest {
    ItemService itemService;

    @BeforeEach
    void setup() {
        itemService = new ItemService(new ItemRepositoryStub(), new StockItemDtoMapper());
    }

    @Test
    void addItem_givenCreateStockItemDto_thenItemIsAdded() {
        CreateItemDto createItemDto = new CreateItemDto("Item1", "Item1 description", 10, 49.99);

        ItemDto expected = itemService.addStockItem(createItemDto);
        ItemDto actual = itemService.getStockDtoItemById(expected.id());

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllStockItems_thenReturnsAllItemsDto() {
        List<ItemDto> expected = add3Items();

        List<ItemDto> actual = itemService.getAllItems();

        actual.forEach(itemDto -> Assertions.assertThat(itemDto).isIn(expected));
    }

    private List<ItemDto> add3Items() {
        List<ItemDto> itemDtos = new ArrayList<>();

        itemDtos.add(itemService.addStockItem(new CreateItemDto("Item1", "Item1 description", 10, 49.99)));
        itemDtos.add(itemService.addStockItem(new CreateItemDto("Item2", "Item2 description", 20, 149.99)));
        itemDtos.add(itemService.addStockItem(new CreateItemDto("Item3", "Item3 description", 30, 249.99)));

        return itemDtos;
    }

}