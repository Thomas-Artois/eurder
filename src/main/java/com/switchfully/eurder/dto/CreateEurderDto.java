package com.switchfully.eurder.dto;

import com.switchfully.eurder.domain.EurderLine;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.repository.UserRepository;
import jakarta.validation.constraints.NotNull;

import java.util.*;

public class CreateEurderDto {
    @NotNull
    private List<EurderLine> eurderList;
    @NotNull
    private User user;
    @NotNull
    private double totalPrice;



    public CreateEurderDto(List<EurderLine> eurderList, User user) {
        this.eurderList = eurderList;
        this.user = user;
        totalPrice = calculatePrice();
    }

    private double calculatePrice() {
        return eurderList.stream()
                .map(EurderLine::getPrice)
                .reduce(0.0, Double::sum);
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
