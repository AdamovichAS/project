package com.github.adamovichas.project.service.util.user.passport;

import com.github.adamovichas.project.model.dto.UserPassportDTO;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserPassportUtil implements IUserPassportUtil {

    private List<Field> passportFields;

    public UserPassportUtil() {
        passportFields = Arrays.asList(UserPassportDTO.class.getDeclaredFields());
    }

    @Override
    public UserPassportDTO createPassport(Map<String, String> passportFieldsAndValues) {
        UserPassportDTO passport = new UserPassportDTO();
        updateFields(passport,passportFieldsAndValues);
        return passport;
    }

    @Override
    public void updateFields(UserPassportDTO passportForUpdate, Map<String, String> fieldsForUpdate) {
        String fieldName;
        for (Field field : passportFields) {
            fieldName = field.getName();
            if(fieldsForUpdate.containsKey(fieldName)){
                updateOneField(passportForUpdate,fieldName, fieldsForUpdate.get(fieldName));
            }
        }
    }

    private void updateOneField(UserPassportDTO passport, String fieldName, String fieldValue){
        switch (fieldName){
            case "userLogin":
                passport.setUserLogin(fieldValue);
                break;
            case "firstName":
                passport.setFirstName(fieldValue);
                break;
            case "lastName":
                passport.setLastName(fieldValue);
                break;
            case "passSeries":
                passport.setPassSeries(fieldValue);
                break;
        }
    }
}
