package com.github.adamovichas.project.service.user;

import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.model.dto.UserDTO;

import java.util.Map;

public enum  UserCreater {
    CREATER;

    private UserFieldsSetter SETTER;

    UserCreater() {
        SETTER = UserFieldsSetter.SETTER;
    }

    public UserDTO createUser(Map<String,String> userFieldsAndValues){
        UserDTO user = new UserDTO();
        user.setRole(Role.USER_NOT_VER);
        SETTER.SetFields(user,userFieldsAndValues);
        return user;
    }

    public UserDTO createAdmin(Map<String,String> userFieldsAndValues){
        UserDTO user = new UserDTO();
        user.setRole(Role.ADMIN);
        SETTER.SetFields(user,userFieldsAndValues);
        return user;
    }
}
