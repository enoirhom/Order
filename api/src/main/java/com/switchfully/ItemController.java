package com.switchfully;

import com.switchfully.security.SecurityService;
import com.switchfully.item.ItemService;
import com.switchfully.item.dto.CreateItemDto;
import com.switchfully.item.dto.ItemDto;
import com.switchfully.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final ItemService itemService;
    private final SecurityService securityService;

    @Autowired
    public ItemController(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestHeader(value = "authorization", required = false) String authorization,
                           @RequestBody CreateItemDto createItemDto) {
        securityService.validate(authorization, Role.ADMIN);
        return itemService.addStockItem(createItemDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }
}
