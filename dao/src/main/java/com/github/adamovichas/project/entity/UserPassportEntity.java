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

    @OneToOne(mappedBy = "userPassportEntity")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
