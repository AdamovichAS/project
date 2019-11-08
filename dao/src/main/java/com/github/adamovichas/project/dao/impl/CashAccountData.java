package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.ICashAccountData;
import com.github.adamovichas.project.entity.CashAccountEntity;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.id.IdentifierGenerationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.RollbackException;

public class CashAccountData implements ICashAccountData {

    private static final Logger log = LoggerFactory.getLogger(DataUser.class);
    private static volatile ICashAccountData instance;

    public static ICashAccountData getInstance() {
        ICashAccountData localInstance = instance;
        if (localInstance == null) {
            synchronized (ICashAccountData.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CashAccountData();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean verification(String login) {
        boolean result = false;
        Session session = HibernateUtil.getSession();
        CashAccountEntity cashAccountEntity = new CashAccountEntity();
        cashAccountEntity.setValue(0);
        cashAccountEntity.setLogin(login);
        try {
            session.getTransaction().begin();
            UserEntity userEntity = session.get(UserEntity.class, login);
            userEntity.setRole(Role.USER_VER);
                cashAccountEntity.setUserEntity(userEntity);
                userEntity.setMoney(cashAccountEntity);

            session.save(cashAccountEntity);
            session.getTransaction().commit();
            result = true;
        }catch (RollbackException | NullPointerException | IdentifierGenerationException e){
            log.error("verification exception Login - {}", login);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return result;
    }

    @Override
    public CashAccountDTO getMoneyByLogin(String login) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        CashAccountEntity cashAccountEntity = session.find(CashAccountEntity.class, login);
        session.getTransaction().commit();
        session.close();
        return EntityDtoViewConverter.getDTO(cashAccountEntity);
    }

    @Override
    public boolean updateMoneyValue(CashAccountDTO money) {
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            final CashAccountEntity cashAccountEntity = session.find(CashAccountEntity.class, money.getLogin());
            cashAccountEntity.setValue(money.getValue());
            session.getTransaction().commit();
            return true;
        }catch (RollbackException e){
            log.error("updateMoney exception Login - {}", money);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }
}
