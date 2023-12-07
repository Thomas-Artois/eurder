package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.dto.CreateItemDto;
import com.switchfully.eurder.dto.ItemDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemServiceTest {
    private ItemService itemService;
    private UserService userService;
    private ItemMapper itemMapper;
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @BeforeEach
    void setUpItemService() {
        itemMapper = new ItemMapper();
        itemRepository = new ItemRepository();
        userMapper = new UserMapper();
        userRepository = new UserRepository();
        userService = new UserService(userMapper, userRepository);
        itemService = new ItemService(itemMapper, itemRepository, userService);
    }

    @Test
    void whenCreateItemInItemService_thenItemIsAddedToItemRepositoryAndReturnsItemDto() {
        //WHEN
        ItemDto itemDto = itemService.createItem(new CreateItemDto("sofa", "stores stuff", 24, 1));

        //THEN
        assertThat(itemDto.getName()).isEqualTo("sofa");
        assertThat(itemService.getAllItems()).contains(itemDto);
        assertThat(itemRepository.getAllItems()).allSatisfy(item -> assertThat(item).isInstanceOf(Item.class));

    }



}
