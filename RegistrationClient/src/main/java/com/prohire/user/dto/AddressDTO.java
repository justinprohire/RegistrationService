package com.prohire.user.dto;

import java.io.Serializable;

public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String doorNumber;
    private String street;
    private String postCode;



    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

