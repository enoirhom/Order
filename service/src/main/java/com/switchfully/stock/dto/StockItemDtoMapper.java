package com.switchfully.stock.dto;

import com.switchfully.item.StockItem;
import org.springframework.stereotype.Component;

@Component
public class StockItemDtoMapper {

    public StockItemDto mapToDto(StockItem stockItem) {
        return new StockItemDto(stockItem.getId(), stockItem.getName(), stockItem.getDescription(), stockItem.getPrice(), stockItem.getQuantity());
    }

    public StockItem mapDtoToStockItem(CreateStockItemDto createStockItemDto) {
        return new StockItem(createStockItemDto.name(), createStockItemDto.description(), createStockItemDto.quantity(), createStockItemDto.price());
    }

}
