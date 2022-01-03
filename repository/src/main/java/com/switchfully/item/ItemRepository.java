package com.switchfully.item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository {
    void addItem(Item item);
    Optional<Item> findItemById(UUID id);
    List<Item> getAllItems();
}
