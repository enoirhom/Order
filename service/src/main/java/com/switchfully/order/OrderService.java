package com.switchfully.order;

import com.switchfully.customer.CustomerService;
import com.switchfully.item.Item;
import com.switchfully.order.dto.CreateOrderDto;
import com.switchfully.order.dto.CreateOrderResponseDto;
import com.switchfully.order.dto.ItemGroupDto;
import com.switchfully.item.ItemService;
import com.switchfully.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final CustomerService customerService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemService itemService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
        this.customerService = customerService;
    }

    public CreateOrderResponseDto addOrder(CreateOrderDto createOrderDto) {
        Customer customer = customerService.getCustomerById(UUID.fromString(createOrderDto.customerId()));
        List<ItemGroup> itemGroups = convertItemGroupsDtoToItemGroups(createOrderDto.itemGroups());
        Order order = new Order(customer, itemGroups);
        orderRepository.addOrder(order);
        return new CreateOrderResponseDto(order.getId(), order.getTotalPrice());
    }

    private List<ItemGroup> convertItemGroupsDtoToItemGroups(List<ItemGroupDto> itemGroupsDto) {
        List<ItemGroup> itemGroups = new ArrayList<>();

        for (ItemGroupDto itemGroupDto : itemGroupsDto) {
            Item item = itemService.getStockItemById(itemGroupDto.itemId());
            item.removeQuantityFromStock(itemGroupDto.quantity());
            itemGroups.add(new ItemGroup(item, itemGroupDto.quantity()));
        }

        return itemGroups;
    }

}
