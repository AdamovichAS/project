package com.github.adamovichas.project.service.util.user;

import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.user.Role;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserUtil implements IUserUtil{

    private List<Field> userFields;

    public UserUtil() {
        userFields = Arrays.asList(UserDTO.class.getDeclaredFields());
    }

    @Override
    public UserDTO createUser(Map<String,String> userFieldsAndValues){
        UserDTO user = new UserDTO();
        user.setRole(Role.USER_NOT_VER);
        updateFields(user,userFieldsAndValues);
        return user;
    }

    @Override
    public void updateFields(UserDTO userForUpdate, Map<String,String> fieldsForUpdate){
        String fieldName;
        for (Field field : userFields) {
            fieldName = field.getName();
            if(fieldsForUpdate.containsKey(fieldName)){
                updateOneField(userForUpdate,fieldName, fieldsForUpdate.get(fieldName));
            }
        }
    }

    private void updateOneField(UserDTO user, String fieldName, String fieldValue){
        switch (fieldName){
            case "login":
                user.setLogin(fieldValue);
                break;
            case "password":
                user.setPassword(fieldValue);
                break;
            case "role":
                user.setRole(Role.valueOf(fieldValue));
                break;
        }
    }
}
