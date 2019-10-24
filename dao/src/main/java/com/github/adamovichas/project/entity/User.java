package com.github.adamovichas.project.entity;


import com.github.adamovichas.project.model.user.Role;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User  {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private int age;
    private String country;
    private Role role;
    private Money money;

    public User() {
    }
    @Id
    @Column(name = "login", nullable = false, updatable = false)
     public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format(
                "Login: %s; |\n" +
                "Password: %s |\n" +
                "Full name: %s %s |\n" +
                "Phone: %s |\n" +
                "Email: %s |\n"+
                "Age: %d |\n" +
                "Country: %s |\n",login,password,firstName,lastName,phone,email,age,country);
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
