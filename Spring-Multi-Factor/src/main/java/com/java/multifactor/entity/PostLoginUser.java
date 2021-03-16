package com.java.multifactor.entity;

import java.sql.Timestamp;

public class PostLoginUser {

    private String email;

    private String fName;

    private String lName;

    private boolean enabled;

    private String otp;

    private Timestamp loggedInTime;

    public PostLoginUser() {
    }

    public PostLoginUser(String email, boolean enabled) {
        this.email = email;
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getLoggedInTime() {
        return loggedInTime;
    }

    public void setLoggedInTime(Timestamp loggedInTime) {
        this.loggedInTime = loggedInTime;
    }
}
