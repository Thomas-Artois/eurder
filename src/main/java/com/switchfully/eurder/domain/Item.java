package com.switchfully.eurder.domain;

import java.util.Objects;
import java.util.UUID;

public class Item {
    private String id;
    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public Item(String name, String description, double price, int amountInStock) {
        this(UUID.randomUUID().toString(), name, description, price, amountInStock);
    }
    public Item(String id, String name, String description, double price, int amountInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
    }

    public Item() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    public Item deepCopy() {
        Item copy = new Item();
        copy.setId(this.getId());
        copy.setName(this.getName());
        copy.setDescription(this.getDescription());
        copy.setPrice(this.getPrice());
        copy.setAmountInStock(this.getAmountInStock());
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(price, item.price) == 0 && amountInStock == item.amountInStock && Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, amountInStock);
    }
}
