package com.switchfully.stock.dto;

public record StockItemDto(String id, String name, String description, double price, int quantity) {
}
