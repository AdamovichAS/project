package com.github.adamovichas.project.service.user;

import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.model.user.User;

import java.util.Map;

public enum  UserCreater {
    CREATER;

    private UserFieldsSetter SETTER;

    UserCreater() {
        SETTER = UserFieldsSetter.SETTER;
    }

    public User createUser(Map<String,String> userFieldsAndValues){
        User user = new User();
        user.setRole(Role.USER_VER);
        SETTER.SetFields(user,userFieldsAndValues);
        return user;
    }

    public User createAdmin(Map<String,String> userFieldsAndValues){
        User user = new User();
        user.setRole(Role.ADMIN);
        SETTER.SetFields(user,userFieldsAndValues);
        return user;
    }
}
