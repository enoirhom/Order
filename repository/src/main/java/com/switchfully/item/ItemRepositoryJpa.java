package com.switchfully.item;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Primary
public class ItemRepositoryJpa implements ItemRepository {
    private final Map<String, Item> stockItems;

    public ItemRepositoryJpa() {
        stockItems = new ConcurrentHashMap<>();
    }

    @Override
    public void addItem(Item stockItem) {
        stockItems.put(stockItem.getId(), stockItem);
    }

    @Override
    public Item getItemById(String id) {
        return stockItems.get(id);
    }

    @Override
    public List<Item> getAllItems() {
        return stockItems.values().stream().toList();
    }

}
