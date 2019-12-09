package com.github.adamovichas.project.service.user;

import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.model.dto.UserDTO;

import java.util.Map;

public class  UserCreater {

    private UserFieldsSetter SETTER;

    public UserCreater() {
        SETTER = UserFieldsSetter.SETTER;
    }

    public UserDTO createUser(Map<String,String> userFieldsAndValues){
        UserDTO user = new UserDTO();
        user.setRole(Role.USER_NOT_VER);
        SETTER.setFields(user,userFieldsAndValues);
        return user;
    }

}
