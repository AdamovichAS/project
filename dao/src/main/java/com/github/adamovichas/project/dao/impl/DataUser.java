package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.entity.MoneyEntity;
import com.github.adamovichas.project.model.dto.MoneyDTO;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.IDataUser;
import com.github.adamovichas.project.IDataConnect;
import com.github.adamovichas.project.util.EntityDtoConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

public enum DataUser implements IDataUser {
    DATA;

    private static final Logger log = LoggerFactory.getLogger(DataUser.class);
    private IDataConnect CONNECTION;

    DataUser() {
        CONNECTION = DataConnect.CONNECT;
    }


    @Override
    public boolean addUser(UserDTO userDTO) {
        UserEntity userEntity = EntityDtoConverter.getEntity(userDTO);
        boolean result = false;
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            MoneyEntity money = new MoneyEntity();
            money.setValue(0);
            money.setUserEntity(userEntity);
            userEntity.setMoney(money);
            entityManager.getTransaction().begin();
            entityManager.persist(userEntity);
            entityManager.persist(money);
            entityManager.getTransaction().commit();
            result = true;
        }catch (RollbackException e){
            log.error("addUser exception User {}",userDTO);
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return result;
    }


    @Override
    public UserDTO getUserByLogin(String login) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        UserDTO user = em.find(UserDTO.class, login);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    public boolean updateUserInfo(UserDTO user) {
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
