package com.switchfully.item;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StockRepositoryStub implements StockRepository {
    private final Map<String, StockItem> stockItems;

    public StockRepositoryStub() {
        stockItems = new ConcurrentHashMap<>();
    }

    public void addItem(StockItem stockItem) {
        stockItems.put(stockItem.getId(), stockItem);
    }

    public StockItem getItemById(String id) {
        return stockItems.get(id);
    }

    public List<StockItem> getAllItems() {
        return stockItems.values().stream().toList();
    }

}
