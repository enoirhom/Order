package com.switchfully.stock;

import com.switchfully.item.Item;
import com.switchfully.item.StockRepository;
import com.switchfully.stock.dto.CreateItemDto;
import com.switchfully.stock.dto.ItemDto;
import com.switchfully.stock.dto.StockItemDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final StockItemDtoMapper stockItemDtoMapper;

    @Autowired
    public StockService(StockRepository stockRepository, StockItemDtoMapper stockItemDtoMapper) {
        this.stockRepository = stockRepository;
        this.stockItemDtoMapper = stockItemDtoMapper;
    }

    public ItemDto addStockItem(CreateItemDto createItemDto) {
        Item itemToAdd = stockItemDtoMapper.mapDtoToStockItem(createItemDto);
        stockRepository.addItem(itemToAdd);
        return stockItemDtoMapper.mapToDto(itemToAdd);
    }

    public Item getStockItemById(String id) {
        return stockRepository.getItemById(id);
    }

    public ItemDto getStockDtoItemById(String id) {
        return stockItemDtoMapper.mapToDto(getStockItemById(id));
    }

    public List<ItemDto> getAllItems() {
        return stockRepository.getAllItems()
                .stream()
                .map(stockItemDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
