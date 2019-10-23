package com.github.adamovichas.project.service.data;



import com.github.adamovichas.project.entity.User;

import java.util.Map;

public interface IdataUserService {

    boolean loginIsExist(String login);
    boolean addNewUser(Map<String,String> userFieldsAndValues);
    boolean userIsExist(String login, String password);
    boolean updateUserInfo(String login, Map<String, String> usersFieldsForUpdateWithLogin);
    User getUserByLogin(String login);
}
