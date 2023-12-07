package com.switchfully.eurder.dto;

import com.switchfully.eurder.domain.EurderLine;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.repository.UserRepository;

import java.util.*;

public class EurderDto {
    private String id;
    private List<EurderLine> eurderList;
    private User user;
    private double totalPrice;



    public EurderDto(String id, List<EurderLine> eurderList, User user) {
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
