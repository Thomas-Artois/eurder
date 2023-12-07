package com.switchfully.eurder.dto;

import java.util.Objects;
import java.util.UUID;

public class ItemDto {
    private String id;
    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public ItemDto(String id, String name, String description, double price, int amountInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Double.compare(price, itemDto.price) == 0 && amountInStock == itemDto.amountInStock && Objects.equals(id, itemDto.id) && Objects.equals(name, itemDto.name) && Objects.equals(description, itemDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, amountInStock);
    }
}
