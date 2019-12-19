package com.github.adamovichas.project.model.dto;

import com.github.adamovichas.project.model.user.Role;

public class AuthUser {

    private String login;
    private Role role;

    public AuthUser() {
    }

    public AuthUser(String login, Role role) {
        this.login = login;
        this.role = role;
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


}
