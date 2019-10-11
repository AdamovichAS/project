package com.github.adamovichas.mysql_data.impl;


import com.github.adamovichas.user.User;


import java.util.List;

public interface IDataUser {
    boolean updateUserInfo(User user);
    String loginIsExist(String login);
    List<String> userIsExist(String login, String password);
    boolean addUser(User user);
    User getUserByLogin(String login);

}
