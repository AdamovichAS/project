package com.github.adamovichas.project.model.dto;

import java.sql.Date;

public class UserPassportDTO {

    private String UserLogin;
    private String firstName;
    private String lastName;
    private String passSeries;

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

}
