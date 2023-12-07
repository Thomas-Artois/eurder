package com.switchfully.eurder.domain;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class EurderLine {
    private Item item;
    private int amount;
    private LocalDate shippingDate;

    public EurderLine(Item item, int amount, LocalDate shippingDate) {
        this.item = new Item(item.getName(), item.getDescription(), item.getPrice(), item.getAmountInStock());
        this.amount = amount;
        this.shippingDate = shippingDate;
    }

    public EurderLine(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    //TODO Add EuderLineDto and CreateEurderLineDto?
    public double getPrice() {
        return item.getPrice()*amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }
}
