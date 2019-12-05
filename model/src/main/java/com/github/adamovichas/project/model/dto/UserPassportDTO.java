package com.github.adamovichas.project.model.dto;

import java.sql.Date;

public class UserPassportDTO {

    private String UserLogin;
    private String firstName;
    private String lastName;
    private String passSeries;
    private int passNumber;
    private Date passIssueDate;
    private Date passEndDate;
    private Date birthDay;
    private String country;
    private String address;

    public String getUserLogin() {
        return UserLogin;
    }

    public void setUserLogin(String userLogin) {
        UserLogin = userLogin;
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

    public String getPassSeries() {
        return passSeries;
    }

    public void setPassSeries(String passSeries) {
        this.passSeries = passSeries;
    }

    public int getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(int passNumber) {
        this.passNumber = passNumber;
    }

    public Date getPassIssueDate() {
        return passIssueDate;
    }

    public void setPassIssueDate(Date passIssueDate) {
        this.passIssueDate = passIssueDate;
    }

    public Date getPassEndDate() {
        return passEndDate;
    }

    public void setPassEndDate(Date passEndDate) {
        this.passEndDate = passEndDate;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
