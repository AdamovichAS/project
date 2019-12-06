package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.dao.IUserPassportDao;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.dto.UserPassportDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, Util.class})
@Transactional()
//@Rollback(value = false)
public class UserPassportDaoTest {

    @Autowired
    private IUserPassportDao passportDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    IUtil util;

    @Test
    public void addPassportTest(){
        UserDTO testUser = util.createTestUser();
        UserPassportDTO passport = util.createTestPassport();
        userDao.addUser(testUser);
        boolean isAdded = passportDao.addPassport(passport);
        assertTrue(isAdded);
    }

    @Test
    public void updateTest(){
        UserDTO testUser = util.createTestUser();
        UserPassportDTO passportDTO = util.createTestPassport();
        userDao.addUser(testUser);
        passportDao.addPassport(passportDTO);
        passportDTO.setPassSeries("MPC");
        passportDTO.setLastName("New");
        passportDTO.setFirstName("New");
        UserPassportDTO passportAfterUpd = passportDao.update(passportDTO);
        assertEquals(passportAfterUpd.getUserLogin(),passportDTO.getUserLogin());
        assertEquals(passportAfterUpd.getFirstName(),passportDTO.getFirstName());
        assertEquals(passportAfterUpd.getLastName(),passportDTO.getLastName());
        assertEquals(passportAfterUpd.getPassSeries(),passportDTO.getPassSeries());
    }
}
