package com.switchfully.order.dto;

import java.util.List;

public record CreateOrderDto(String customerId, List<ItemGroupDto> itemGroups) {
}
