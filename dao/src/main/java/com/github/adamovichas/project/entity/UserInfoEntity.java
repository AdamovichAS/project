package com.github.adamovichas.project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "user_info")
public class UserInfoEntity {

    private String UserLogin;
    private String phone;
    private String email;
    private UserEntity userEntity;

    public UserInfoEntity(){}

    @Id
    @Column(name = "user_login", nullable = false, updatable = false)
    @GenericGenerator(
            name = "gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "userEntity")
    )
    @GeneratedValue(generator = "gen")
    public String getUserLogin() {
        return UserLogin;
    }

    public void setUserLogin(String userlogin) {
        this.UserLogin = userlogin;
    }

    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
