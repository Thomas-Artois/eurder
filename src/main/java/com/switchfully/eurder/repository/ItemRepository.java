package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}
