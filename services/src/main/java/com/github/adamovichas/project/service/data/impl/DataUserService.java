package com.github.adamovichas.project.service.data.impl;
import com.github.adamovichas.project.service.user.UserCreater;
import com.github.adamovichas.project.service.user.UserFieldsSetter;
import com.github.adamovichas.project.service.data.IdataUserService;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.dao.IUserDao;

import java.util.Map;


import static java.util.Objects.nonNull;

public class DataUserService implements IdataUserService {

    private UserCreater userCreater = new UserCreater();
    private final IUserDao data;

    public DataUserService(IUserDao dataUser) {
        this.data = dataUser;
    }

    @Override
    public boolean loginIsExist(String login)  {
        boolean result = false;
        UserDTO userByLogin = data.getUserByLogin(login);
        if(nonNull(userByLogin)){
            if(userByLogin.getLogin().equals(login)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean addNewUser(Map<String,String> userFieldsAndValues) {
        String login = userFieldsAndValues.get("login");
        if(loginIsExist(login)){
            return false;
        }
        UserDTO user = userCreater.createUser(userFieldsAndValues);
        return data.addUser(user);
    }

    @Override
    public boolean userIsExist(String login, String password) {
        boolean result = false;
        final UserDTO userByLogin = data.getUserByLogin(login);
        if(nonNull(userByLogin)){
            if(userByLogin.getPassword().equals(password)){
                result = true;
            }
        }
        return result;

    }

    @Override
    public boolean updateUserInfo(String login, Map<String, String> userFieldsForUpdate) {
        UserDTO user = data.getUserByLogin(login);
        if(user.getLogin() == null){
            return false;
        }
        UserFieldsSetter.SETTER.SetFields(user,userFieldsForUpdate);
        return data.updateUser(user);
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return data.getUserByLogin(login);
    }

}
