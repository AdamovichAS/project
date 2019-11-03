package com.github.adamovichas.project.dao.impl;


import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

public class DataUserTest {

    private DataUser dataUser = (DataUser) DataUser.getInstance();
    private Util util = Util.UTIL_TEST;

    private static EntityManager entityManager;

    @BeforeAll
    static void init() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @AfterAll
    public static void cleanUp() {
        entityManager.close();
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

    @Test
    public void updateUserInfo(){
        UserDTO userDTO = util.createTestUser();
        dataUser.addUser(userDTO);
        userDTO.setPassword("newPas");
        userDTO.setEmail("newMail");
        dataUser.updateUserInfo(userDTO);
        UserDTO userByLogin = dataUser.getUserByLogin(userDTO.getLogin());
        UserEntity entity = EntityDtoViewConverter.getEntity(userDTO);
        util.deleteTestUser(entity);
        assertEquals(userByLogin.getPassword(), userDTO.getPassword());
        assertEquals(userByLogin.getEmail(), userDTO.getEmail());
    }

    @Test
    public void cacheTest(){
        dataUser.getUserByLogin("alexey");
        dataUser.getUserByLogin("alexey");
        dataUser.getUserByLogin("alexey");
    }

}
