package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.dto.CreateItemDto;
import com.switchfully.eurder.dto.ItemDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item mapCreateItemDtoToItem(CreateItemDto createItemDto) {
        return new Item(createItemDto.getName(), createItemDto.getDescription(), createItemDto.getPrice(), createItemDto.getAmountInStock());
    }

    public ItemDto mapItemToItemDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getAmountInStock());
    }

}
