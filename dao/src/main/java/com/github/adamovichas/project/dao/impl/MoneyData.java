package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.IMoneyData;
import com.github.adamovichas.project.entity.MoneyEntity;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.dto.MoneyDTO;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import com.github.adamovichas.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.id.IdentifierGenerationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.RollbackException;

import static java.util.Objects.nonNull;

public class MoneyData implements IMoneyData {

    private static final Logger log = LoggerFactory.getLogger(DataUser.class);
    private static volatile IMoneyData instance;

    public static IMoneyData getInstance() {
        IMoneyData localInstance = instance;
        if (localInstance == null) {
            synchronized (IMoneyData.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MoneyData();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean createMoney(String login) {
        boolean result = false;
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
        MoneyEntity moneyEntity = new MoneyEntity();
        moneyEntity.setValue(0);
        moneyEntity.setLogin(login);
        try {
            session.getTransaction().begin();
            UserEntity userEntity = session.get(UserEntity.class, login);
                moneyEntity.setUserEntity(userEntity);
                userEntity.setMoney(moneyEntity);

            session.save(moneyEntity);
            session.getTransaction().commit();
            result = true;
        }catch (RollbackException | NullPointerException | IdentifierGenerationException e){
            log.error("createMoney exception Login - {}", login);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return result;
    }

    @Override
    public MoneyDTO getMoneyByLogin(String login) {
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
        session.getTransaction().begin();
        MoneyEntity moneyEntity = session.find(MoneyEntity.class, login);
        return EntityDtoViewConverter.getDTO(moneyEntity);
    }
}
