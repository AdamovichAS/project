package com.github.adamovichas.project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "user_passport")
public class UserPassportEntity {

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
    private UserEntity userEntity;


    public UserPassportEntity(){}

    @Id
    @Column(name = "user_login", nullable = false, updatable = false)
    public String getUserLogin() {
        return UserLogin;
    }

    public void setUserLogin(String userLogin) {
        UserLogin = userLogin;
    }
    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "pass_series", nullable = false)
    public String getPassSeries() {
        return passSeries;
    }

    public void setPassSeries(String passSeries) {
        this.passSeries = passSeries;
    }
    @Column(name = "pass_number", nullable = false)
    public int getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(int passNumber) {
        this.passNumber = passNumber;
    }
    @Column(name = "pass_issue_date", nullable = false)
    public Date getPassIssueDate() {
        return passIssueDate;
    }

    public void setPassIssueDate(Date passIssueDate) {
        this.passIssueDate = passIssueDate;
    }
    @Column(name = "pass_end_date", nullable = false)
    public Date getPassEndDate() {
        return passEndDate;
    }

    public void setPassEndDate(Date passEndDate) {
        this.passEndDate = passEndDate;
    }
    @Column(name = "birth_day", nullable = false)
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @OneToOne
    @JoinColumn(name = "user_login", referencedColumnName = "login")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
