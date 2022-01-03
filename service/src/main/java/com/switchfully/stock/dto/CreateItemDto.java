package com.switchfully.stock.dto;

public record CreateItemDto(String name, String description, int quantity, double price) {
}
