package com.github.adamovichas.project;


import com.github.adamovichas.project.model.dto.UserDTO;

public interface IDataUser {
    boolean updateUserInfo(UserDTO user);
//    User loginIsExist(String login);
//    User userIsExist(String login, String password);
    boolean addUser(UserDTO user);
    UserDTO getUserByLogin(String login);

}
