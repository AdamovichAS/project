package com.github.adamovichas.project.entity;

public class Bet {

    private Long id;
    private String userLogin;
    private Long factorId;
    private int money;

    public Bet(String userLogin, Long factorId, int money) {
        this.userLogin = userLogin;
        this.factorId = factorId;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getFactorId() {
        return factorId;
    }

    public void setFactorId(Long factorId) {
        this.factorId = factorId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
