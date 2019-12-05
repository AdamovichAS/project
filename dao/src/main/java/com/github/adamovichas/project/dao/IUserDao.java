package com.github.adamovichas.project.dao;


import com.github.adamovichas.project.model.dto.UserDTO;

public interface IUserDao {
    boolean updateUser(UserDTO user);
    void addUser(UserDTO user);
    UserDTO getUserByLogin(String login);
    void block(String login);
}
