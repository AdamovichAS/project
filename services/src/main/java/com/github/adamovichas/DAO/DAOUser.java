package com.github.adamovichas.DAO;
import com.github.adamovichas.DAO.impl.IDAOUser;
import com.github.adamovichas.mysql_data.DataUser;
import com.github.adamovichas.user.User;
import com.github.adamovichas.mysql_data.impl.IDataUser;
import com.github.adamovichas.user.UserCreater;
import com.github.adamovichas.user.UserFieldsSetter;

import java.util.List;
import java.util.Map;


import static java.util.Objects.nonNull;

public enum DAOUser implements IDAOUser {
    DAO_USER;

    private IDataUser data;

    DAOUser() {
        data  = DataUser.DATA;
    }

    @Override
    public boolean loginIsExist(String login)  {
        boolean result = false;
        String loginData = data.loginIsExist(login);
        if(nonNull(loginData)){
            if(loginData.equals(login)){
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
        data.addUser(user);
        return true;
    }

    @Override
    public boolean userIsExist(String login, String password) {
        boolean result = false;
        List<String> loginPassData = data.userIsExist(login, password);
        if(!loginPassData.isEmpty()){
            if(loginPassData.get(0).equals(login) && loginPassData.get(1).equals(password)){
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
        User user = data.getUserByLogin(login);
        return user;
    }

}
