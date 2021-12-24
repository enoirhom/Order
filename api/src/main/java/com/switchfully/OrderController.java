package com.switchfully;

import com.switchfully.order.OrderService;
import com.switchfully.order.dto.CreateOrderDto;
import com.switchfully.order.dto.CreateOrderResponseDto;
import com.switchfully.security.SecurityService;
import com.switchfully.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;
    private final SecurityService securityService;

    @Autowired
    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponseDto addOrder(@RequestHeader String authorization, @RequestBody CreateOrderDto createOrderDto) {
        securityService.validate(authorization, Role.CUSTOMER, createOrderDto.customerId());
        return orderService.addOrder(createOrderDto);
    }

}
