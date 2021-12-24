package com.switchfully.stock;

import com.switchfully.StockRepository;
import com.switchfully.item.StockItem;
import com.switchfully.stock.dto.CreateStockItemDto;
import com.switchfully.stock.dto.StockItemDto;
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

    public StockItemDto addStockItem(CreateStockItemDto createStockItemDto) {
        StockItem stockItemToAdd = stockItemDtoMapper.mapDtoToStockItem(createStockItemDto);
        stockRepository.addItem(stockItemToAdd);
        return stockItemDtoMapper.mapToDto(stockItemToAdd);
    }

    public StockItem getStockItemById(String id) {
        return stockRepository.getItemById(id);
    }

    public StockItemDto getStockDtoItemById(String id) {
        return stockItemDtoMapper.mapToDto(getStockItemById(id));
    }

    public List<StockItemDto> getAllItems() {
        return stockRepository.getAllItems()
                .stream()
                .map(stockItemDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
