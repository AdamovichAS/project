package com.github.adamovichas.project.dao.impl;


import com.github.adamovichas.project.dao.IDataUser;
import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.config.SettingsConfig;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, SettingsConfig.class,Util.class})
@Transactional
@Rollback()
public class DataUserTest {

    @Autowired
    private IDataUser dataUser;
    @Autowired
    private IUtil util;


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
