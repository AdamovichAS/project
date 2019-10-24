package com.github.adamovichas.project.dao.impl;


import com.github.adamovichas.project.entity.User;
import com.github.adamovichas.project.util.HibernateUtil;
import com.github.adamovichas.project.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataUserTest {

    private DataUser dataUser = DataUser.DATA;
    private UtilTest util = UtilTest.UTIL_TEST;

    @AfterAll
    public static void cleanUp() {
        HibernateUtil.close();
    }

    @Test
    public void addUser(){
        User testUser = util.createTestUser();
//        dataUser.addUser(testUser);
//        User userByLogin = dataUser.getUserByLogin(testUser.getLogin());
//        assertEquals(testUser.getLogin(), userByLogin.getLogin());
//        assertEquals(testUser.getPassword(), userByLogin.getPassword());
//        util.deleteTestUser(testUser.getLogin());
        boolean result = dataUser.addUser(testUser);
        assertTrue(result);
 //       util.deleteTestUser(testUser);

//        Session session = SessionFactoryUtil.getSession();
//        session.beginTransaction();
//        session.save(testUser);
//        session.getTransaction().commit();
//        session.close();
    }

    @Test
    public void getUserByLogin(){
        User testUser = util.createTestUser();
        dataUser.addUser(testUser);
        User userByLogin = dataUser.getUserByLogin(testUser.getLogin());
        assertEquals(testUser.getLogin(), userByLogin.getLogin());
        assertEquals(testUser.getPassword(), userByLogin.getPassword());
        util.deleteTestUser(testUser);
    }


}
