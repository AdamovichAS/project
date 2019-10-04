package by.itacademy.jd2.user_service;

import by.itacademy.jd2.user.User;
import java.util.Map;

public enum  UserCreater {
    CREATER;

    private UserFieldsSetter SETTER;

    UserCreater() {
        SETTER = UserFieldsSetter.SETTER;
    }

    public User createUser(Map<String,String> userFieldsAndValues){
        User user = new User();
        SETTER.SetFields(user,userFieldsAndValues);
        return user;
    }
}
