package com.switchfully.item.dto;

public record CreateItemDto(String name, String description, int quantity, double price) {
}
