package com.github.adamovichas.project.service.data.impl;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.service.util.user.IUserUtil;
import com.github.adamovichas.project.service.util.user.passport.IUserPassportUtil;

import java.util.Map;


import static java.util.Objects.nonNull;

public class UserService implements IUserService {

    private final IUserUtil userUtil;
    private final IUserPassportUtil userPassportUtil;
    private final IUserDao userDao;

    public UserService(IUserDao userDao, IUserUtil userUtil, IUserPassportUtil userPassportUtil) {
        this.userUtil = userUtil;
        this.userPassportUtil = userPassportUtil;
        this.userDao = userDao;
    }

    /**
     *User
     */

    @Override
    public boolean loginIsExist(String login)  {
        boolean result = false;
        UserDTO userByLogin = userDao.getUserByLogin(login);
        if(nonNull(userByLogin)){
            if(userByLogin.getLogin().equals(login)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public String addNewUser(Map<String,String> userFieldsAndValues) {
        String login = userFieldsAndValues.get("login");
        if(loginIsExist(login)){
            return String.format("Login - %s is exist",login);
        }
        UserDTO user = userUtil.createUser(userFieldsAndValues);
        userDao.addUser(user);
        return null;
    }

    @Override
    public boolean userIsExist(String login, String password) {
        boolean result = false;
        final UserDTO userByLogin = userDao.getUserByLogin(login);
        if(nonNull(userByLogin)){
            if(userByLogin.getPassword().equals(password)){
                result = true;
            }
        }
        return result;

    }

    @Override
    public boolean updateUser(String login, Map<String, String> userFieldsForUpdate) {
        UserDTO user = userDao.getUserByLogin(login);
        if(user == null){
            return false;
        }
        userUtil.updateFields(user,userFieldsForUpdate);
        return userDao.updateUser(user);
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    /**
     * UserPassport
     * @return
     */

    @Override
    public UserPassportDTO addUserPassport(Map<String, String> passportFieldsAndValues) {
        String login = passportFieldsAndValues.get("userLogin");
//        UserDTO userByLogin = userDao.getUserByLogin(login);
//        if(userByLogin == null){
//            return false;
//        }
        UserPassportDTO passport = userPassportUtil.createPassport(passportFieldsAndValues);

        return userDao.addPassport(passport);
    }

    @Override
    public UserPassportDTO updatePassport(String login, Map<String, String> passportFieldsForUpdate) {
        UserPassportDTO passport = userDao.getPassport(login);
//        if(passport == null){
//            return false;
//        }
        return userDao.updatePassport(passport);
    }

    @Override
    public UserPassportDTO getPassport(String login) {
        return userDao.getPassport(login);
    }

    /**
     * UserCashAccount
     * @return
     */

    @Override
    public CashAccountDTO addUserCashAccount(String login) {
       return userDao.addUserCashAccount(login);
    }

    @Override
    public CashAccountDTO getCashAccountByLogin(String login) {
        return userDao.getCashAccountByLogin(login);
    }

    @Override
    public CashAccountDTO updateCashAccountValue(CashAccountDTO cashAccountDTO) {
        return userDao.updateCashAccountValue(cashAccountDTO);
    }


}
