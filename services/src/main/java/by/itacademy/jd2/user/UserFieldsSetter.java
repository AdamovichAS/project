package by.itacademy.jd2.user;

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
        Field[] authUserFields = User.class.getSuperclass().getDeclaredFields();
        Field[] userField = User.class.getDeclaredFields();
        userFields.addAll(Arrays.asList(authUserFields));
        userFields.addAll(Arrays.asList(userField));
    }


    public void SetFields(User userForSet, Map<String,String> fieldsForSet){
        String fieldName;
        for (Field field : userFields) {
            fieldName = field.getName();
            if(fieldsForSet.containsKey(fieldName)){
                setOneFieldValue(userForSet,fieldName,fieldsForSet.get(fieldName));
            }
        }
    }

    private void setOneFieldValue(User user, String fieldName, String fieldValue){
        switch (fieldName){
            case "login":
                user.setLogin(fieldValue);
                break;
            case "password":
                user.setPassword(fieldValue);
                break;
            case "firstName":
                user.setFirstName(fieldValue);
                break;
            case "lastName":
                user.setLastName(fieldValue);
                break;
            case "phone":
                user.setPhone(fieldValue);
                break;
            case "email":
                user.setEmail(fieldValue);
                break;
            case "age":
                user.setAge(Integer.parseInt(fieldValue));
                break;
            case "country":
                user.setCountry(fieldValue);
                break;
            case "dto":
                user.setRole(Role.valueOf(fieldValue));
                break;
        }
    }
}
