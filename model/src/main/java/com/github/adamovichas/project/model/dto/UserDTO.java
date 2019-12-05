package com.github.adamovichas.project.model.dto;


import com.github.adamovichas.project.model.user.Role;

public class UserDTO {

    private String login;
    private String password;
    private Role role;

    public UserDTO() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    //    @Override
//    public String toString() {
//        return String.format(
//                "Login: %s; |\n" +
//                "Password: %s |\n" +
//                "Full name: %s %s |\n" +
//                "Phone: %s |\n" +
//                "Email: %s |\n"+
//                "Age: %d |\n" +
//                "Country: %s |\n",login,password,firstName,lastName,phone,email,age,country);
//    }
}
