package com.switchfully.stub;

import com.switchfully.item.Item;
import com.switchfully.item.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepositoryStub implements ItemRepository {
    private final Map<String, Item> stockItems;

    public ItemRepositoryStub() {
        stockItems = new ConcurrentHashMap<>();
    }

    public void addItem(Item stockItem) {
        stockItems.put(stockItem.getId(), stockItem);
    }

    public Item getItemById(String id) {
        return stockItems.get(id);
    }

    public List<Item> getAllItems() {
        return stockItems.values().stream().toList();
    }

}
