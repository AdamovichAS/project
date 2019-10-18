package com.github.adamovichas.project.model.user;


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

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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
}
