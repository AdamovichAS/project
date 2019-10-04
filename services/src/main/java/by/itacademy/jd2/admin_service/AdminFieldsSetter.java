package by.itacademy.jd2.admin_service;

import by.itacademy.jd2.user.Admin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum  AdminFieldsSetter {
    SETTER;

    private List<Field> adminFields;

    AdminFieldsSetter() {
        adminFields = new ArrayList<>();
        Field[] authUserFields = Admin.class.getSuperclass().getDeclaredFields();
        Field[] userField = Admin.class.getDeclaredFields();
        adminFields.addAll(Arrays.asList(authUserFields));
        adminFields.addAll(Arrays.asList(userField));
    }



    public void setFields(Map<String, String> fieldsAndValues, Admin admin) {
        String fieldForSet;
        for (Field field : adminFields) {
            fieldForSet = field.getName();
            if(fieldsAndValues.containsKey(fieldForSet)){
                setOneFieldValue(admin,fieldForSet,fieldsAndValues.get(fieldForSet));
            }
        }
    }

    private void setOneFieldValue(Admin admin, String fieldName, String fieldValue){
        switch (fieldName){
            case "login":
                admin.setLogin(fieldValue);
                break;
            case "password":
                admin.setPassword(fieldValue);
                break;
            case "firstName":
                admin.setFirstName(fieldValue);
                break;
            case "lastName":
                admin.setLastName(fieldValue);
                break;
            case "phone":
                admin.setPhone(fieldValue);
                break;
            case "email":
                admin.setEmail(fieldValue);
                break;
        }
    }
}
