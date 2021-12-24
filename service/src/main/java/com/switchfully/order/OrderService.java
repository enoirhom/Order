package com.switchfully.order;

import com.switchfully.OrderRepository;
import com.switchfully.customer.CustomerService;
import com.switchfully.item.ItemGroup;
import com.switchfully.item.StockItem;
import com.switchfully.order.dto.CreateOrderDto;
import com.switchfully.order.dto.CreateOrderResponseDto;
import com.switchfully.order.dto.ItemGroupDto;
import com.switchfully.stock.StockService;
import com.switchfully.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final StockService stockService;
    private final CustomerService customerService;

    @Autowired
    public OrderService(OrderRepository orderRepository, StockService stockService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
        this.customerService = customerService;
    }

    public CreateOrderResponseDto addOrder(CreateOrderDto createOrderDto) {
        Customer customer = customerService.getCustomerById(createOrderDto.customerId());
        List<ItemGroup> itemGroups = convertItemGroupsDtoToItemGroups(createOrderDto.itemGroups());
        Order order = new Order(customer, itemGroups);
        orderRepository.addOrder(order);
        return new CreateOrderResponseDto(order.getId(), order.getTotalPrice());
    }

    private List<ItemGroup> convertItemGroupsDtoToItemGroups(List<ItemGroupDto> itemGroupsDto) {
        List<ItemGroup> itemGroups = new ArrayList<>();

        for (ItemGroupDto itemGroupDto : itemGroupsDto) {
            StockItem stockItem = stockService.getStockItemById(itemGroupDto.itemId());
            stockItem.removeQuantityFromStock(itemGroupDto.quantity());
            itemGroups.add(new ItemGroup(stockItem, itemGroupDto.quantity()));
        }

        return itemGroups;
    }

}
