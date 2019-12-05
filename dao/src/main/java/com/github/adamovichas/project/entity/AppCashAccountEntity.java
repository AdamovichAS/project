package com.github.adamovichas.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "app_cash_account")
public class AppCashAccountEntity {
    private Long id;
    private double balance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "balance", nullable = false)
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
