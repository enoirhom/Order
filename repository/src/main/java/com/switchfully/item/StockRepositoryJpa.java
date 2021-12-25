package com.switchfully.item;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("!test")
public class StockRepositoryJpa implements StockRepository {
    private final Map<String, StockItem> stockItems;

    public StockRepositoryJpa() {
        stockItems = new ConcurrentHashMap<>();
    }

    @Override
    public void addItem(StockItem stockItem) {
        stockItems.put(stockItem.getId(), stockItem);
    }

    @Override
    public StockItem getItemById(String id) {
        return stockItems.get(id);
    }

    @Override
    public List<StockItem> getAllItems() {
        return stockItems.values().stream().toList();
    }

}
