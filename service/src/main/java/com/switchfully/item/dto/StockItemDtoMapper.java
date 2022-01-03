package com.switchfully.item.dto;

import com.switchfully.item.Item;
import org.springframework.stereotype.Component;

@Component
public class StockItemDtoMapper {

    public ItemDto mapToDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getStockQuantity());
    }

    public Item mapDtoToStockItem(CreateItemDto createItemDto) {
        return new Item(createItemDto.name(), createItemDto.description(), createItemDto.quantity(), createItemDto.price());
    }

}
