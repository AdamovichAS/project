package com.github.adamovichas.project.dao.impl;


import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.user.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class,Util.class})
@Transactional()
//@Rollback(value = false)
public class UserDaoTest {

    @Autowired
    private IUserDao dataUser;
    @Autowired
    private IUtil util;


    @Test
    public void addUser() {
        UserDTO testUser = util.createTestUser();
        dataUser.addUser(testUser);
        final UserDTO userByLogin = dataUser.getUserByLogin(testUser.getLogin());
        assertEquals(userByLogin.getLogin(),testUser.getLogin());
        assertEquals(userByLogin.getPassword(),testUser.getPassword());
        assertEquals(userByLogin.getRole(),testUser.getRole());
    }

    @Test
    public void getUserByLogin() {
        UserDTO testUser = util.createTestUser();
        dataUser.addUser(testUser);
        final UserDTO userByLogin = dataUser.getUserByLogin(testUser.getLogin());
        assertEquals(userByLogin.getLogin(),testUser.getLogin());
        assertEquals(userByLogin.getPassword(),testUser.getPassword());
        assertEquals(userByLogin.getRole(),testUser.getRole());
    }

    @Test
    public void updateUser(){
        UserDTO testUser = util.createTestUser();
        dataUser.addUser(testUser);
        testUser.setPassword("newPas");
        testUser.setRole(Role.USER_NOT_VER);
        final boolean isUpdated = dataUser.updateUser(testUser);
        UserDTO userByLogin = dataUser.getUserByLogin(testUser.getLogin());
        assertTrue(isUpdated);
        assertEquals(userByLogin.getLogin(),testUser.getLogin());
        assertEquals(userByLogin.getPassword(),testUser.getPassword());
        assertEquals(userByLogin.getRole(),testUser.getRole());
    }

    @Test
    public void deleteUser(){
        UserDTO testUser = util.createTestUser();
        dataUser.addUser(testUser);
        dataUser.block(testUser.getLogin());
        final UserDTO userByLogin = dataUser.getUserByLogin(testUser.getLogin());
        assertEquals(userByLogin.getRole(),Role.BLOCKED);
    }
    

}
