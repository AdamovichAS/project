package com.github.adamovichas.project.service.data.impl;
import com.github.adamovichas.project.service.user.UserCreater;
import com.github.adamovichas.project.service.user.UserFieldsSetter;
import com.github.adamovichas.project.service.data.IdataUserService;
import com.github.adamovichas.project.entity.User;
import com.github.adamovichas.project.dao.impl.DataUser;
import com.github.adamovichas.project.IDataUser;

import java.util.List;
import java.util.Map;


import static java.util.Objects.nonNull;

public class DataUserService implements IdataUserService {

    private IDataUser data =DataUser.DATA;

    private static volatile IdataUserService instance;

    public static IdataUserService getInstance() {
        IdataUserService localInstance = instance;
        if (localInstance == null) {
            synchronized (IdataUserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataUserService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean loginIsExist(String login)  {
        boolean result = false;
        User userByLogin = data.getUserByLogin(login);
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
        User user = UserCreater.CREATER.createUser(userFieldsAndValues);
        return data.addUser(user);
    }

    @Override
    public boolean userIsExist(String login, String password) {
        boolean result = false;
        final User userByLogin = data.getUserByLogin(login);
        if(nonNull(userByLogin)){
            if(userByLogin.getPassword().equals(password)){
                result = true;
            }
        }
        return result;

    }

    @Override
    public boolean updateUserInfo(String login, Map<String, String> userFieldsForUpdate) {
        User user = data.getUserByLogin(login);
        if(user.getLogin() == null){
            return false;
        }
        UserFieldsSetter.SETTER.SetFields(user,userFieldsForUpdate);
        return data.updateUserInfo(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return data.getUserByLogin(login);
    }

}
