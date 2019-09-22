package by.itacademy.jd2.user;


import java.lang.reflect.Field;

public class User {

    private int money;

    private String login;

    private String name;

    private String lastName;

    private String password;

    private ROLE role;

    private STATUS status;

    public User() {
    }

    public User(String login, String password, int money, ROLE role) {
        this.money = money;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return login + "\n"+
                password + "\n"+
                money + "\n"+
                role +"\n";
    }

    public enum ROLE {
        USER, ADMIN
    }

    public enum STATUS{
        NOT_VERIFIERD, VERIFIERD, BLOCKED
    }
}
