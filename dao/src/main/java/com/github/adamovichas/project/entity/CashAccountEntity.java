package com.github.adamovichas.project.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Cache;
import javax.persistence.*;

@Entity
@Table(name = "cash_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CashAccountEntity {

    private String userLogin;
    private double value;
    private UserEntity userEntity;


    public CashAccountEntity() {
    }

    @Id
    @Column(name = "user_login", nullable = false, updatable = false)
    @GenericGenerator(
            name = "gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "userEntity")
    )
    @GeneratedValue(generator = "gen")
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String login) {
        this.userLogin = login;
    }


    @Column(name = "value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_login", referencedColumnName = "login")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity user) {
        this.userEntity = user;
    }
}
