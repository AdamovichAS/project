package com.github.adamovichas.project;


import com.github.adamovichas.project.entity.User;

public interface IDataUser {
    boolean updateUserInfo(User user);
//    User loginIsExist(String login);
//    User userIsExist(String login, String password);
    boolean addUser(User user);
    User getUserByLogin(String login);

}
