package com.github.adamovichas.mysql_data.impl;

import com.github.adamovichas.event.Event;
import com.github.adamovichas.event.Factor;
import com.github.adamovichas.event.FactorName;
import com.github.adamovichas.user.Role;
import com.github.adamovichas.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum UtilTest {
    UTIL_TEST;

    User createTestUser(){
        User user = new User();
        user.setLogin("test");
        user.setPassword("123");
        user.setFirstName("name");
        user.setLastName("lastName");
        user.setPhone("567");
        user.setEmail("mail");
        user.setAge(18);
        user.setCountry("bel");
        user.setRole(Role.USER);
        return  user;
    }

    void deleteTestUser(String login){
        try (Connection connect = DataConnect.CONNECT.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM user WHERE login =?;")){
            preparedStatement.setString(1,login);
            try {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     Event createEventTest(){
        Event event = new Event(1L,2L, Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        List<Factor> factors= new ArrayList<>();
        factors.add(new Factor(FactorName.win,2.5));
        factors.add(new Factor(FactorName.draw,3));
        factors.add(new Factor(FactorName.lose,2.1));
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
