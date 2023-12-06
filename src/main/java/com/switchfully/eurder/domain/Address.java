package com.switchfully.eurder.domain;

import jakarta.validation.constraints.NotEmpty;

public class Address {
    @NotEmpty
    private String streetName;
    @NotEmpty
    private String streetNumber;
    @NotEmpty
    private String postalCode;
    @NotEmpty
    private String city;

    public Address(String streetName, String streetNumber, String postalCode, String city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

}
