package com.switchfully;

import com.switchfully.security.SecurityService;
import com.switchfully.stock.StockService;
import com.switchfully.stock.dto.CreateItemDto;
import com.switchfully.stock.dto.ItemDto;
import com.switchfully.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final StockService stockService;
    private final SecurityService securityService;

    @Autowired
    public ItemController(StockService stockService, SecurityService securityService) {
        this.stockService = stockService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestHeader(value = "authorization", required = false) String authorization,
                           @RequestBody CreateItemDto createItemDto) {
        securityService.validate(authorization, Role.ADMIN);
        return stockService.addStockItem(createItemDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAllItems() {
        return stockService.getAllItems();
    }
}
