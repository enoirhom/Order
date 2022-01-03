package com.switchfully.item;

import com.switchfully.item.dto.CreateItemDto;
import com.switchfully.item.dto.ItemDto;
import com.switchfully.item.dto.StockItemDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final StockItemDtoMapper stockItemDtoMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, StockItemDtoMapper stockItemDtoMapper) {
        this.itemRepository = itemRepository;
        this.stockItemDtoMapper = stockItemDtoMapper;
    }

    public ItemDto addStockItem(CreateItemDto createItemDto) {
        Item itemToAdd = stockItemDtoMapper.mapDtoToStockItem(createItemDto);
        itemRepository.addItem(itemToAdd);
        return stockItemDtoMapper.mapToDto(itemToAdd);
    }

    public Item getStockItemById(UUID id) {
        return itemRepository.findItemById(id).get();
    }

    public ItemDto getStockDtoItemById(UUID id) {
        return stockItemDtoMapper.mapToDto(getStockItemById(id));
    }

    public List<ItemDto> getAllItems() {
        return itemRepository.getAllItems()
                .stream()
                .map(stockItemDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
