package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.IDataUser;
import com.github.adamovichas.project.ISessionHibernate;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.RollbackException;

import static java.util.Objects.nonNull;

public class DataUser implements IDataUser {

    private static final Logger log = LoggerFactory.getLogger(DataUser.class);
    private final ISessionHibernate factory;


    public DataUser(ISessionHibernate emf) {
        factory = emf;
    }


    @Override
    public boolean addUser(UserDTO userDTO) {
        UserEntity userEntity = EntityDtoViewConverter.getEntity(userDTO);
        boolean result = false;
        Session session = factory.getSession();
        try {
            session.getTransaction().begin();
            session.save(userEntity);
            session.getTransaction().commit();
            result = true;
        } catch (RollbackException e) {
            log.error("addUser exception User - {}", userDTO);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }


    @Override
    public UserDTO getUserByLogin(String login) {
        Session session = factory.getSession();
        UserDTO userDTO = null;
        try {
            session.getTransaction().begin();
            UserEntity user = session.find(UserEntity.class, login);
            session.getTransaction().commit();
            if(nonNull(user)){
                userDTO = EntityDtoViewConverter.getDTO(user);
            }
            return userDTO;
        } catch (RollbackException e) {
            log.error("getUserByLogin exception Login - {}", login);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        throw new RuntimeException();
    }

    @Override
    public boolean updateUserInfo(UserDTO user) {
        boolean result = false;
        Session session = factory.getSession();
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
