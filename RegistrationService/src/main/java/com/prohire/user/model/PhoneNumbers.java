package com.prohire.user.model;

import java.io.Serializable;

public class PhoneNumbers implements Serializable {
    private static final long serialVersionUID =1L;

    private String mobile;
    private String work;
    private String home;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
