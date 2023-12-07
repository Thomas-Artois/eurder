package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.exception.ItemNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {
    private Map<String, Item> items = new HashMap<>();

    public ItemRepository() {
        List<Item> listOfItems = List.of(
                new Item("Closet", "Stores clothes", 45.5, 2),
                new Item("Table", "Stores stuff", 145.5, 5),
                new Item("Chair", "Stores people", 25.5, 0)
        );

        listOfItems.forEach(this::createItem);
    }

    public Item createItem(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    public List<Item> getAllItems() {
        return items.values().stream().toList();
    }

    public Item getItemById(String id) throws ItemNotFoundException{
        return items.values().stream().filter(item -> item.getId().equals(id)).findFirst().orElseThrow(ItemNotFoundException::new);
    }

    public Item getItemByName(String name) {
        return items.values().stream().filter(item -> item.getName().equals(name)).findFirst().orElseThrow(ItemNotFoundException::new);
    }


}
