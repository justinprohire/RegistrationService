package com.prohire.user.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable{
    private  static final long serialVersionUID =1L;

    private String registrationStatus;
    private String emailAddress;
    private String password;

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
