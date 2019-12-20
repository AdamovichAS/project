package com.github.adamovichas.project.model.dto;

import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;

public class AuthUser {

    private String login;
    private Role role;
    private VereficationStatus status;


    public AuthUser(String login, Role role, VereficationStatus status) {
        this.login = login;
        this.role = role;
        this.status = status;
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

    public VereficationStatus getStatus() {
        return status;
    }

    public void setStatus(VereficationStatus status) {
        this.status = status;
    }
}
