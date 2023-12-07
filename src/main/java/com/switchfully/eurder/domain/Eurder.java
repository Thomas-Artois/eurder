package com.switchfully.eurder.domain;

import java.util.*;

public class Eurder {
    private String id;
    private List<EurderLine> eurderLineList;
    private User user;
    private double totalPrice;

    public Eurder(List<EurderLine> eurderLineList, User user) {
        this(UUID.randomUUID().toString(), eurderLineList, user);
    }


    public Eurder(String id, List<EurderLine> eurderLineList, User user) {
        this.id = id;
        this.eurderLineList = eurderLineList;
        this.user = user;
        totalPrice = calculatePrice();
    }

    private double calculatePrice() {
        return eurderLineList.stream()
                .map(EurderLine::getPrice)
                .reduce(0.0, Double::sum);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EurderLine> getEurderLineList() {
        return eurderLineList;
    }

    public void setEurderLineList(List<EurderLine> eurderLineList) {
        this.eurderLineList = eurderLineList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
