package com.github.adamovichas.project.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

@Entity
@Table(name = "money")
public class MoneyEntity {

    private String login;
    private int value;
    private UserEntity userEntity;

    public MoneyEntity() {
    }

    @Id
    @Column(name = "user_login", nullable = false, updatable = false)
    @GenericGenerator(
            name = "gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "userEntity")
    )
    @GeneratedValue(generator = "gen")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @OneToOne
    @JoinColumn(name = "user_login", referencedColumnName = "login",foreignKey = @ForeignKey(ConstraintMode.PROVIDER_DEFAULT))
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity user) {
        this.userEntity = user;
    }
}
