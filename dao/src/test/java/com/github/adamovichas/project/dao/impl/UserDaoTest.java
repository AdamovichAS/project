package com.github.adamovichas.project.dao.impl;


import com.github.adamovichas.project.config.HibernateConfig;

import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.config.DaoConfig;

import com.github.adamovichas.project.dao.impl.util.IUtil;
import com.github.adamovichas.project.dao.impl.util.Util;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import com.github.adamovichas.project.model.user.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, Util.class})
@Transactional()
//@Rollback(value = false)
public class UserDaoTest {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUtil util;


    @Test
    public void addUser() {
        UserDTO testUser = util.createTestUser();
        userDao.addUser(testUser);
        final UserDTO userByLogin = userDao.getUserByLogin(testUser.getLogin());
        assertEquals(userByLogin.getLogin(),testUser.getLogin());
        assertEquals(userByLogin.getPassword(),testUser.getPassword());
        assertEquals(userByLogin.getRole(),testUser.getRole());
    }

    @Test
    public void getUserByLoginTrue() {
        UserDTO testUser = util.createTestUser();
        userDao.addUser(testUser);
        final UserDTO userByLogin = userDao.getUserByLogin(testUser.getLogin());
        assertEquals(userByLogin.getLogin(),testUser.getLogin());
        assertEquals(userByLogin.getPassword(),testUser.getPassword());
        assertEquals(userByLogin.getRole(),testUser.getRole());
    }

    @Test
    public void getUserByLoginNull(){
        final UserDTO testUser = util.createTestUser();
        final UserDTO userByLogin = userDao.getUserByLogin(testUser.getLogin());
        assertNull(userByLogin);
    }

    @Test
    public void updateUser(){
        UserDTO testUser = util.createTestUser();
        userDao.addUser(testUser);
        testUser.setPassword("newPas");
        testUser.setRole(Role.USER_NOT_VER);
        final boolean isUpdated = userDao.updateUser(testUser);
        UserDTO userByLogin = userDao.getUserByLogin(testUser.getLogin());
        assertTrue(isUpdated);
        assertEquals(userByLogin.getLogin(),testUser.getLogin());
        assertEquals(userByLogin.getPassword(),testUser.getPassword());
        assertEquals(userByLogin.getRole(),testUser.getRole());
    }

    @Test
    public void deleteUser(){
        UserDTO testUser = util.createTestUser();
        userDao.addUser(testUser);
        userDao.blockUser(testUser.getLogin());
        final UserDTO userByLogin = userDao.getUserByLogin(testUser.getLogin());
        assertEquals(userByLogin.getRole(),Role.BLOCKED);
    }

    /**
     *CashAccount
     */

    @Test
    public void addCashAccount(){
        UserDTO userDTO = util.createTestUser();
        userDao.addUser(userDTO);
        userDao.addUserCashAccount(userDTO.getLogin());
        CashAccountDTO cashAccountDTO = userDao.getCashAccountByLogin(userDTO.getLogin());
        assertEquals(cashAccountDTO.getLogin(),userDTO.getLogin());
        assertEquals(cashAccountDTO.getValue(),0);
    }

    @Test
    public void getAccountByLogin(){
        UserDTO userDTO = util.createTestUser();
        userDao.addUser(userDTO);
        userDao.addUserCashAccount(userDTO.getLogin());
        CashAccountDTO moneyByLogin = userDao.getCashAccountByLogin(userDTO.getLogin());
        assertEquals(userDTO.getLogin(),moneyByLogin.getLogin());
        assertEquals(0,moneyByLogin.getValue());
    }

    @Test
    public void updateCashAccountValue(){
        double newCashAccountValue = 100;
        UserDTO testUser = util.createTestUser();
        userDao.addUser(testUser);
        userDao.addUserCashAccount(testUser.getLogin());
        CashAccountDTO account = userDao.getCashAccountByLogin(testUser.getLogin());
        assertEquals(account.getValue(),0);
        account.setValue(newCashAccountValue);
        CashAccountDTO cashAccountDTOafterUpdate = userDao.updateCashAccountValue(account);
        assertEquals(cashAccountDTOafterUpdate.getLogin(),testUser.getLogin());
        assertEquals(cashAccountDTOafterUpdate.getValue(),newCashAccountValue);
    }

    /**
     * UserPassport
     */

//    @Test
//    public void addPassportTest(){
//        UserDTO testUser = util.createTestUser();
//        UserPassportDTO passport = util.createTestPassport();
//        userDao.addUser(testUser);
//        userDao.addPassport(passport);
//        UserPassportDTO passportAdded = userDao.getPassport(testUser.getLogin());
//        assertNotNull(passportAdded);
//    }

//    @Test
//    public void updateTest(){
//        UserDTO testUser = util.createTestUser();
//        UserPassportDTO passportDTO = util.createTestPassport();
//        userDao.addUser(testUser);
//        userDao.addPassport(passportDTO);
//        passportDTO.setPassSeries("MPC");
//        passportDTO.setLastName("New");
//        passportDTO.setFirstName("New");
//        UserPassportDTO passportAfterUpd = userDao.updatePassport(passportDTO);
//        assertEquals(passportAfterUpd.getUserLogin(),passportDTO.getUserLogin());
//        assertEquals(passportAfterUpd.getFirstName(),passportDTO.getFirstName());
//        assertEquals(passportAfterUpd.getLastName(),passportDTO.getLastName());
//        assertEquals(passportAfterUpd.getPassSeries(),passportDTO.getPassSeries());
//    }
}
