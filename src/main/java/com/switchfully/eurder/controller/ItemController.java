package com.switchfully.eurder.controller;

import com.switchfully.eurder.dto.CreateItemDto;
import com.switchfully.eurder.dto.ItemDto;
import com.switchfully.eurder.repository.UserRepository;
import com.switchfully.eurder.service.ItemService;
import com.switchfully.eurder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(path = "/items")
public class ItemController {
    private ItemService itemService;
    private UserService userService;

    public ItemController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@Valid @RequestBody CreateItemDto createItemDto, @RequestParam String email, @RequestParam String password) {
        userService.checkIfUserIsAdmin(email, password);
        return itemService.createItem(createItemDto);
    }

}
