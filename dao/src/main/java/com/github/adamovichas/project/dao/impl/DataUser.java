package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.entity.User;
import com.github.adamovichas.project.IDataUser;
import com.github.adamovichas.project.IDataConnect;
import com.github.adamovichas.project.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.RollbackException;
import java.sql.*;

public enum DataUser implements IDataUser {
    DATA;

    private static final Logger log = LoggerFactory.getLogger(DataUser.class);
    private IDataConnect CONNECTION;

    DataUser() {
        CONNECTION = DataConnect.CONNECT;
    }


    @Override
    public boolean addUser(User user) {
        boolean result = false;
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            result = true;
        }catch (RollbackException e){
            log.error("addUser exception User {}",user);
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return result;
    }


    @Override
    public User getUserByLogin(String login) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, login);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    public boolean updateUserInfo(User user) {
        boolean result = false;
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(em.contains(user) ? user : em.merge(user));
            em.getTransaction().commit();
            result =true;
        }catch (RollbackException e){
            em.getTransaction().rollback();
            log.error("updateUserInfo exception User {}",user);
        }finally {
            em.close();
        }
        return result;
        }
    }
