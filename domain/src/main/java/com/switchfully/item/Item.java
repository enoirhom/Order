package com.switchfully.item;

import java.util.UUID;

class Item {
    final String id;
    String name;
    String description;

    public Item(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

}
