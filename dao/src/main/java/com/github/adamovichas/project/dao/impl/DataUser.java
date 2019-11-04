package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.IDataUser;
import com.github.adamovichas.project.IDataConnect;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import static java.util.Objects.nonNull;

public class DataUser implements IDataUser {

    private static final Logger log = LoggerFactory.getLogger(DataUser.class);

    private static volatile IDataUser instance;

    public static IDataUser getInstance() {
        IDataUser localInstance = instance;
        if (localInstance == null) {
            synchronized (IDataUser.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataUser();
                }
            }
        }
        return localInstance;
    }


    @Override
    public boolean addUser(UserDTO userDTO) {
        UserEntity userEntity = EntityDtoViewConverter.getEntity(userDTO);
        boolean result = false;
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userEntity);
            entityManager.getTransaction().commit();
            result = true;
        } catch (RollbackException e) {
            log.error("addUser exception User - {}", userDTO);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return result;
    }


    @Override
    public UserDTO getUserByLogin(String login) {
        EntityManager em = HibernateUtil.getEntityManager();
        UserDTO userDTO = null;
        try {
            em.getTransaction().begin();
            UserEntity user = em.find(UserEntity.class, login);
            em.getTransaction().commit();
            if(nonNull(user)){
                userDTO = EntityDtoViewConverter.getDTO(user);
            }
            return userDTO;
        } catch (RollbackException e) {
            log.error("getUserByLogin exception Login - {}", login);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        throw new RuntimeException();
    }

    @Override
    public boolean updateUserInfo(UserDTO user) {
        boolean result = false;
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            UserEntity userEntity = session.find(UserEntity.class, user.getLogin());
            setDTOfieldsToEntity(userEntity,user);
            session.update(userEntity);
            session.getTransaction().commit();
            result = true;
        } catch (RollbackException e) {
            session.getTransaction().rollback();
            log.error("updateUserInfo exception User {}", user);
        } finally {
            session.close();
        }
        return result;
    }

    private void setDTOfieldsToEntity(UserEntity userEntity, UserDTO userDTO){
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setCountry(userDTO.getCountry());
        userEntity.setRole(userDTO.getRole());
        userEntity.setAge(userDTO.getAge());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setPassword(userDTO.getPassword());
    }
}
