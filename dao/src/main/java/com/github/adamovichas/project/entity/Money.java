package com.github.adamovichas.project.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

@Entity
@Table(name = "money")
public class Money {

    private String login;
    private int value;

    public Money() {
    }

    public Money(String login, int value) {
        this.login = login;
        this.value = value;
    }
    @Id
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "money"))
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

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
