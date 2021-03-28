package com.java.multifactor.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer {

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "pwd")
    private String password;

    @Column(name = "first_name")
    private String fName;

    @Column(name = "last_name")
    private String lName;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "one_time_password")
    private String oneTimePassword;

    @Column(name = "otp_requested_time")
    private Date otpRequestedTime;

    public Customer() {
    }

    public boolean isOTPRequired() {
        if(null!=oneTimePassword){
            long currentTimeInMillis = System.currentTimeMillis();
            long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();

            if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
                // OTP expires
                return false;
            }
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public Date getOtpRequestedTime() {
        return otpRequestedTime;
    }

    public void setOtpRequestedTime(Date otpRequestedTime) {
        this.otpRequestedTime = otpRequestedTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", enabled=" + enabled +
                ", oneTimePassword='" + oneTimePassword + '\'' +
                ", otpRequestedTime=" + otpRequestedTime +
                '}';
    }
}
