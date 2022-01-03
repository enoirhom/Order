package com.switchfully.item;

import java.util.List;

public interface ItemRepository {
    void addItem(Item stockItem);
    Item getItemById(String id);
    List<Item> getAllItems();
}
