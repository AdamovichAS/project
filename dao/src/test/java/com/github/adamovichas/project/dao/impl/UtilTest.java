package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.dto.EventDTO;
import com.github.adamovichas.project.entity.MoneyEntity;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;
import com.github.adamovichas.project.model.dto.UserDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum UtilTest {
    UTIL_TEST;

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
        MoneyEntity moneyEntity = session.get(MoneyEntity.class, login);
        session.delete(moneyEntity);
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
        EventDTO event = new EventDTO("Arsenal","Aston Vila", Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        List<FactorDTO> factors= new ArrayList<>();
        factors.add(new FactorDTO(FactorName.win,2.5));
        factors.add(new FactorDTO(FactorName.draw,3));
        factors.add(new FactorDTO(FactorName.lose,2.1));
        event.setFactors(factors);
        return event;
    }

     void deleteEvent(Long id) {
        Connection connection;
        try {
            connection = DataConnect.CONNECT.connect();
            connection.setAutoCommit(false);
            try (PreparedStatement statementFactor = connection.prepareStatement("DELETE FROM factor_event WHERE event_id = ?;");
                 PreparedStatement statementEvent = connection.prepareStatement("DELETE FROM event WHERE id = ?;")) {
                statementEvent.setLong(1, id);
                statementFactor.setLong(1, id);
                statementFactor.executeUpdate();
                statementEvent.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int getCountAllNotFinishedEvents(){
        Integer count = null;
        try (Connection connect = DataConnect.CONNECT.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT count(*) as count from event where result is null;")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
