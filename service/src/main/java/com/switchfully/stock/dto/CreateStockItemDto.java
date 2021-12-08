package com.switchfully.stock.dto;

public record CreateStockItemDto(String name, String description, double price, int quantity) {
}
