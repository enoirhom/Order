package com.switchfully.stub;

import com.switchfully.item.Item;
import com.switchfully.item.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepositoryStub implements ItemRepository {
    private final Map<UUID, Item> stockItems;

    public ItemRepositoryStub() {
        stockItems = new ConcurrentHashMap<>();
    }

    @Override
    public void addItem(Item item) {
        stockItems.put(item.getId(), item);
    }

    @Override
    public Optional<Item> findItemById(UUID id) {
        return Optional.of(stockItems.get(id));
    }

    @Override
    public List<Item> getAllItems() {
        return stockItems.values().stream().toList();
    }

}
