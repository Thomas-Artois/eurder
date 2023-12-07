package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.dto.CreateItemDto;
import com.switchfully.eurder.dto.ItemDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private ItemMapper itemMapper;
    private ItemRepository itemRepository;
    private UserService userService;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository, UserService userService) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    public ItemDto createItem(CreateItemDto createItemDto) {


        Item item = itemRepository.createItem(itemMapper.mapCreateItemDtoToItem(createItemDto));
        return itemMapper.mapItemToItemDto(item);
    }

    public List<ItemDto> getAllItems() {
        return itemRepository.getAllItems().stream().map(item -> itemMapper.mapItemToItemDto(item)).collect(Collectors.toList());
    }
}