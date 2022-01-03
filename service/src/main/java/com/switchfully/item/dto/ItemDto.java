package com.switchfully.item.dto;

import java.util.UUID;

public record ItemDto(UUID id, String name, String description, double price, int quantity) {
}
