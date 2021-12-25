package com.switchfully.item;

import java.util.List;

public interface StockRepository {
    void addItem(StockItem stockItem);
    StockItem getItemById(String id);
    List<StockItem> getAllItems();
}
