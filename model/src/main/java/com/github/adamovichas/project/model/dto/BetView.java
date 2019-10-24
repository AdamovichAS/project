package com.github.adamovichas.project.model.dto;

import com.github.adamovichas.project.model.factor.FactorName;

public class BetView {
    private Long id;
    private String login;
    private String event;
    private FactorName factorName;
    private double factorValue;
    private int money;

    public BetView() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public FactorName getFactorName() {
        return factorName;
    }

    public void setFactorName(FactorName factorName) {
        this.factorName = factorName;
    }

    public double getFactorValue() {
        return factorValue;
    }

    public void setFactorValue(double factorValue) {
        this.factorValue = factorValue;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return String.format("BET ID: %d | Login: %s | Event: %s | %s: %.2f | Money in bet: %d",id,login,event,factorName,factorValue,money);
    }
}
