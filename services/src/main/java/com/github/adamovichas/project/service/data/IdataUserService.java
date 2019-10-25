package com.github.adamovichas.project.service.data;

import com.github.adamovichas.project.model.dto.UserDTO;


import java.util.Map;

public interface IdataUserService {

    boolean loginIsExist(String login);
    boolean addNewUser(Map<String,String> userFieldsAndValues);
    boolean userIsExist(String login, String password);
    boolean updateUserInfo(String login, Map<String, String> usersFieldsForUpdateWithLogin);
    UserDTO getUserByLogin(String login);
}
