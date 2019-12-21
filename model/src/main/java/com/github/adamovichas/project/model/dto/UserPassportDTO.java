package com.github.adamovichas.project.model.dto;

import com.github.adamovichas.project.model.user.passport.VereficationStatus;

public class UserPassportDTO {

    private String userLogin;
    private String firstName;
    private String lastName;
    private String passSeries;
    private String passFileName;
    private VereficationStatus vereficationStatus;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

    public String getPassFileName() {
        return passFileName;
    }

    public void setPassFileName(String passFileName) {
        this.passFileName = passFileName;
    }

    public VereficationStatus getVereficationStatus() {
        return vereficationStatus;
    }

    public void setVereficationStatus(VereficationStatus vereficationStatus) {
        this.vereficationStatus = vereficationStatus;
    }

    @Override
    public String toString() {
        return String.format("Login: %s | first name: %s | last name: %s | passport series: %s | verification status: %s", userLogin,firstName,lastName,passSeries, vereficationStatus);
    }
}
