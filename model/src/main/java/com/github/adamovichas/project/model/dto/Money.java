package com.github.adamovichas.project.model.dto;

public class Money {

    private String login;
    private int value;

    public Money() {
    }

    public Money(String login, int value) {
        this.login = login;
        this.value = value;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
