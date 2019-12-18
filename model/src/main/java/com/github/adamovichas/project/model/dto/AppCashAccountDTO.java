package com.github.adamovichas.project.model.dto;

public class AppCashAccountDTO {
    private Long id;
    private double balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Application balance is %.2f",balance);
    }
}
