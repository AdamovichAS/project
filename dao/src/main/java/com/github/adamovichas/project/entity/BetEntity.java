package com.github.adamovichas.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bet")
public class BetEntity {
    private Long id;
    private String userLogin;
    private Long factorId;
    private double money;
    private FactorEntity factor;
    private UserEntity user;

    public BetEntity(String userLogin, Long factorId, int money) {
        this.userLogin = userLogin;
        this.factorId = factorId;
        this.money = money;
    }

    public BetEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user", nullable = false, insertable = false, updatable = false)
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Column(name = "factor_event_id", nullable = false, insertable = false, updatable = false)
    public Long getFactorId() {
        return factorId;
    }

    public void setFactorId(Long factorId) {
        this.factorId = factorId;
    }

    @Column(name = "money_for_bet", nullable = false)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @ManyToOne
    @JoinColumn(name = "factor_event_id", referencedColumnName = "id")
    public FactorEntity getFactor() {
        return factor;
    }

    public void setFactor(FactorEntity factor) {
        this.factor = factor;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "login")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
