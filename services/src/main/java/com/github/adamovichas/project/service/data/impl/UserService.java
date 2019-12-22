package com.github.adamovichas.project.service.data.impl;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.service.util.user.IUserUtil;
import com.github.adamovichas.project.service.util.user.passport.IUserPassportUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


import static java.util.Objects.nonNull;

public class UserService implements IUserService {

    private static final int PAGE_SIZE = 2;

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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
    public boolean updateUser(String login, Map<String, String> userFieldsForUpdate) {
        UserDTO user = userDao.getUserByLogin(login);
        if(user == null){
            return false;
        }
        userUtil.updateFields(user,userFieldsForUpdate);
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public UserDTO getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    /**
     * UserPassport
     * @return
     */

    @Override
    @Transactional
    public UserPassportDTO addUserPassport(String login, Map<String, String> passportFieldsAndValues) {
        UserPassportDTO passport = userPassportUtil.createPassport(login,passportFieldsAndValues);
        return userDao.addPassport(passport);
    }

    @Override
    @Transactional
    public UserPassportDTO updatePassport(String login, Map<String, String> passportFieldsForUpdate) {
        UserPassportDTO passport = userDao.getPassport(login);
        if(passport == null){
            passport = addUserPassport(login,passportFieldsForUpdate);
        }else {
            userPassportUtil.updateFields(passport, passportFieldsForUpdate);
        }
        return userDao.updatePassport(passport);
    }


    @Override
    @Transactional
    public void verifyUser(String login, VereficationStatus newStatus){
        UserPassportDTO passport = userDao.getPassport(login);
        passport.setVereficationStatus(newStatus);
        userDao.updatePassport(passport);
        CashAccountDTO cashAccountDTO = userDao.getCashAccountByLogin(login);
        if(cashAccountDTO == null){
            userDao.addUserCashAccount(login);
        }

    }

    @Override
    @Transactional
    public UserPassportDTO getPassport(String login) {
        return userDao.getPassport(login);
    }

    @Override
    @Transactional
    public List<UserPassportDTO> getPassportOnPageByVerificationStatusWaitAndRoleUser(int page) {
        return userDao.getPassportOnPageByVerificationStatusWaitAndRoleUser(page - 1,PAGE_SIZE);
    }

    @Override
    @Transactional
    public Long getPassportMaxPagesByVerificationStatusWaiting(){
        Long rowsEvent = userDao.getCountPassportPagesByVerificationStatusWaitingAndRoleUser();
        Long maxPages = rowsEvent / PAGE_SIZE;
        if (rowsEvent % PAGE_SIZE > 0) {
            maxPages++;
        }
        return maxPages;
    }

}
