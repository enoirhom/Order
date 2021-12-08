package com.switchfully.stock.dto;

public record CreateStockItemDto(String name, String description, int quantity, double price) {
}
