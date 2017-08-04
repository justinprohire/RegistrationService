package com.prohire.user.model;

import com.prohire.entity.json.PHAggregatedEntity;
import com.prohire.entity.json.PHEntityID;

import java.io.Serializable;
import java.util.UUID;

@PHAggregatedEntity(schemaName = "user_service")
public class SystemClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @PHEntityID
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String emailAddress;
    private AddressModel address;
    private PhoneNumbers phoneNumbers;
    private String password;
    private boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
