package com.github.adamovichas.project.project.dao;


import com.github.adamovichas.project.model.user.User;


import java.util.List;

public interface IDataUser {
    boolean updateUserInfo(User user);
    String loginIsExist(String login);
    List<String> userIsExist(String login, String password);
    boolean addUser(User user);
    User getUserByLogin(String login);

}
