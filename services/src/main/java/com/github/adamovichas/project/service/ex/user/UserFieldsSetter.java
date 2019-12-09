package com.github.adamovichas.project.service.user;

import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.model.dto.UserDTO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum  UserFieldsSetter {
    SETTER;

    private List<Field> userFields;


    UserFieldsSetter() {
        userFields = new ArrayList<>();
        Field[] userField = UserDTO.class.getDeclaredFields();
        Field[] passportFields = UserPassportDTO.class.getDeclaredFields();
        userFields.addAll(Arrays.asList(userField));
        userFields.addAll(Arrays.asList(passportFields));
    }


//    public void setFields(UserDTO userForSet, Map<String,String> fieldsForSet){
//        String fieldName;
//        for (Field field : userFields) {
//            fieldName = field.getName();
//            if(fieldsForSet.containsKey(fieldName)){
//                setOneFieldValue(userForSet,fieldName,fieldsForSet.get(fieldName));
//            }
//        }
//    }

//    public void setFields(UserPassportDTO passportForSet, Map<String,String> fieldsForSet){
//        String fieldName;
//        for (Field field : userFields) {
//            fieldName = field.getName();
//            if(fieldsForSet.containsKey(fieldName)){
//                setOneFieldValue(userForSet,fieldName,fieldsForSet.get(fieldName));
//            }
//        }
//    }

//    private void setOneFieldValue(UserDTO user, String fieldName, String fieldValue){
//        switch (fieldName){
//            case "login":
//                user.setLogin(fieldValue);
//                break;
//            case "password":
//                user.setPassword(fieldValue);
//                break;
//            case "role":
//                user.setRole(Role.valueOf(fieldValue));
//                break;
//        }
//    }
}
