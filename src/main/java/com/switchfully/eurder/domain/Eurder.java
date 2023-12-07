package com.switchfully.eurder.domain;

import com.switchfully.eurder.repository.UserRepository;

import java.util.*;

public class Eurder {
    private String id;
    private List<EurderLine> eurderList;
    private User user;
    private double totalPrice;

    public Eurder(List<EurderLine> eurderList, User user) {
        this(UUID.randomUUID().toString(), eurderList, user);
    }


    public Eurder(String id, List<EurderLine> eurderList, User user) {
        this.id = id;
        this.eurderList = eurderList;
        this.user = user;
        totalPrice = calculatePrice();
    }

    private double calculatePrice() {
        return eurderList.stream()
                .map(EurderLine::getPrice)
                .reduce(0.0, Double::sum);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EurderLine> getEurderList() {
        return eurderList;
    }

    public void setEurderList(List<EurderLine> eurderList) {
        this.eurderList = eurderList;
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
