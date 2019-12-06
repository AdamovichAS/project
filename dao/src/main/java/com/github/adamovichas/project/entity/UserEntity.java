package com.github.adamovichas.project.entity;


import com.github.adamovichas.project.model.user.Role;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity {

    private String login;
    private String password;
    private Role role;
    private UserPassportEntity userPassportEntity;
    private CashAccountEntity cashAccount;
    private List<BetEntity>bets;

    public UserEntity() {
    }
    @Id
    @Column(name = "login", nullable = false, updatable = false)
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @OneToOne(mappedBy = "userEntity",fetch = FetchType.EAGER)
    public CashAccountEntity getCashAccount() {
        return cashAccount;
    }
    public void setCashAccount(CashAccountEntity money) {
        this.cashAccount = money;
    }

    @Column(name = "role", columnDefinition = "USER_VER")
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    public List<BetEntity> getBets() {
        return bets;
    }
    public void setBets(List<BetEntity> bets) {
        this.bets = bets;
    }

    @OneToOne(mappedBy = "userEntity")
//    @PrimaryKeyJoinColumn
    public UserPassportEntity getUserPassportEntity() {
        return userPassportEntity;
    }

    public void setUserPassportEntity(UserPassportEntity userPassportEntity) {
        this.userPassportEntity = userPassportEntity;
    }
}
