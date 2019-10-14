package com.github.adamovichas.project.project.dao.impl;


import com.github.adamovichas.project.web.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataUserTest {

    private DataUser dataUser = DataUser.DATA;
    private UtilTest util = UtilTest.UTIL_TEST;


    @Test
    public void addUser(){
        User testUser = util.createTestUser();
        dataUser.addUser(testUser);
        User userByLogin = dataUser.getUserByLogin(testUser.getLogin());
        assertEquals(testUser.getLogin(), userByLogin.getLogin());
        assertEquals(testUser.getPassword(), userByLogin.getPassword());
        util.deleteTestUser(testUser.getLogin());
    }

    @Test
    public void userIsExist(){
        User testUser = util.createTestUser();
        List<String> logPassEmpty = dataUser.userIsExist(testUser.getLogin(), testUser.getPassword());
        assertTrue(logPassEmpty.isEmpty());
        dataUser.addUser(testUser);
        List<String> logPas = dataUser.userIsExist(testUser.getLogin(), testUser.getPassword());
        assertFalse(logPas.isEmpty());
        util.deleteTestUser(testUser.getLogin());
    }

    @Test
    public void getUserByLogin(){
        User testUser = util.createTestUser();
        dataUser.addUser(testUser);
        User userByLogin = dataUser.getUserByLogin(testUser.getLogin());
        assertEquals(testUser.getLogin(), userByLogin.getLogin());
        assertEquals(testUser.getPassword(), userByLogin.getPassword());
        util.deleteTestUser(testUser.getLogin());
    }

    @Test
    public void loginIsExist(){
        User testUser = util.createTestUser();
        String loginNull = dataUser.loginIsExist(testUser.getLogin());
        assertNull(loginNull);
        dataUser.addUser(testUser);
        String login = dataUser.loginIsExist(testUser.getLogin());
        assertEquals(login,testUser.getLogin());
        util.deleteTestUser(testUser.getLogin());
    }
}
