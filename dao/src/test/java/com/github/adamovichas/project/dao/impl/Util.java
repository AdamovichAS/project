package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.entity.EventEntity;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.entity.CashAccountEntity;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum Util {
    UTIL_TEST;

    private static final Logger log = LoggerFactory.getLogger(DataUser.class);

    UserDTO createTestUser(){
        UserDTO user = new UserDTO();
        user.setLogin("test");
        user.setPassword("123");
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setPhone("567");
        user.setEmail("mail");
        user.setAge(18);
        user.setCountry("bel");
        user.setRole(Role.USER_VER);
        return  user;
    }

    void deleteTestUser(UserEntity user){
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(user) ? user : em.merge(user));
        em.getTransaction().commit();
        em.close();
    }

    void deleteDeposit(String login){
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
        session.getTransaction().begin();
        CashAccountEntity cashAccountEntity = session.get(CashAccountEntity.class, login);
        session.delete(cashAccountEntity);
        session.getTransaction().commit();
        session.close();
    }

    BetDTO createFinishedBet(){
        final UserDTO testUser = createTestUser();
        BetDTO betDTO = new BetDTO();
        betDTO.setUserLogin(testUser.getLogin());
        betDTO.setMoney(100);
        betDTO.setFactorId(1L);
        return betDTO;
    }

    BetDTO createNotFinishedBet(){
        final UserDTO testUser = createTestUser();
        BetDTO betDTO = new BetDTO();
        betDTO.setUserLogin(testUser.getLogin());
        betDTO.setMoney(100);
        betDTO.setFactorId(5L);
        return betDTO;
    }

     EventDTO createEventTest(){
        EventDTO event = new EventDTO("Arsenal","Aston Vila", 1L, Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        List<FactorDTO> factors= new ArrayList<>();
        factors.add(new FactorDTO(FactorName.win,2.5));
        factors.add(new FactorDTO(FactorName.draw,3));
        factors.add(new FactorDTO(FactorName.lose,2.1));
        event.setFactors(factors);
        return event;
    }

     void deleteEvent(Long id) {
         Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
         try{
             session.getTransaction().begin();
             EventEntity eventEntity = session.find(EventEntity.class, id);
            session.delete(eventEntity);
            session.getTransaction().commit();
         }catch (RollbackException e){
             log.error("deleteEvent exception, Id -  {}",id);
             session.getTransaction().rollback();
         }finally {
             session.close();
         }
     }

    Long getCountAllNotFinishedEvents(){
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT count(*) FROM EventEntity e WHERE e.resultFactorId = null");
        List list = query.list();
        if (list.isEmpty()){
            return 0L;
        }
        return (Long) list.get(0);
    }
}