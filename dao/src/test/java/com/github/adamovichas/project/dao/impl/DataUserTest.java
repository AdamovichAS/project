package com.github.adamovichas.project.dao.impl;


import com.github.adamovichas.project.IDataUser;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataUserTest {

    private DataUser dataUser = (DataUser) DataUser.getInstance();
    private UtilTest util = UtilTest.UTIL_TEST;

    @AfterAll
    public static void cleanUp() {
        HibernateUtil.closeEMFactory();
    }

    @Test
    public void addUser() {
        UserDTO testUser = util.createTestUser();
        UserEntity entity = EntityDtoViewConverter.getEntity(testUser);
        boolean result = dataUser.addUser(testUser);
        assertTrue(result);
        util.deleteTestUser(entity);

    }

    @Test
    public void getUserByLogin() {
        UserDTO testUser = util.createTestUser();
        UserEntity entity = EntityDtoViewConverter.getEntity(testUser);
        dataUser.addUser(testUser);
        UserDTO userByLogin = dataUser.getUserByLogin(testUser.getLogin());
        assertEquals(testUser.getLogin(), userByLogin.getLogin());
        assertEquals(testUser.getPassword(), userByLogin.getPassword());
        util.deleteTestUser(entity);
    }


}
