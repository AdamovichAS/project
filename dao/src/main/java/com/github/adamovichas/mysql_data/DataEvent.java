package com.github.adamovichas.mysql_data;

import com.github.adamovichas.dto.EventView;
import com.github.adamovichas.dto.League;
import com.github.adamovichas.dto.Team;
import com.github.adamovichas.event.Event;
import com.github.adamovichas.event.Factor;
import com.github.adamovichas.event.FactorName;
import com.github.adamovichas.mysql_data.impl.IDataConnect;
import com.github.adamovichas.mysql_data.impl.IDataEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum DataEvent implements IDataEvent {
    DATA_EVENT;

    private IDataConnect CONNECTION;

    DataEvent() {
        CONNECTION = DataConnect.CONNECT;
    }

    @Override
    public Long addEvent(Event event) {
        Connection connection = null;
        try {
            connection = CONNECTION.connect();
            connection.setAutoCommit(false);
            try (PreparedStatement statementEvent = connection.prepareStatement("INSERT INTO data.event(team_one, team_two, start_time, end_time) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement statementFactor = connection.prepareStatement("INSERT INTO data.factor_event(name, value, event_id) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statementEvent.setLong(1, event.getTeamOneId());
                statementEvent.setLong(2, event.getTeamTwoId());
                statementEvent.setTimestamp(3, event.getStartTime());
                statementEvent.setTimestamp(4, event.getEndTime());
                statementEvent.executeUpdate();
                final ResultSet generatedKeys = statementEvent.getGeneratedKeys();
                generatedKeys.next();
                final long idEvent = generatedKeys.getLong(1);
                List<Factor> factors = event.getFactors();
                for (Factor factor : factors) {
                    statementFactor.setString(1, factor.getName().toString());
                    statementFactor.setDouble(2, factor.getValue());
                    statementFactor.setLong(3, idEvent);
                    statementFactor.addBatch();
                }
                statementFactor.executeBatch();
                /*final ResultSet factorKeys = statementFactor.getGeneratedKeys();
                List<Factor>resultFactors = new ArrayList<>();
                for (Factor factor : factors) {
                    factorKeys.next();
                    final long id = factorKeys.getLong(1);
                    resultFactors.add(new Factor(id, factor.getName(), factor.getValue(), idEvent));
                }
                Event resultEvent = new Event(event.getTeamOneId(),event.getTeamTwoId(),event.getStartTime(),event.getEndTime());
                resultEvent.setId(idEvent);
                resultEvent.setFactors(resultFactors);*/
                connection.commit();
                return idEvent;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }

            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Event eventIsExist(Event event) {
        Event result = null;
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT team_one,team_two, start_time, end_time FROM  event WHERE team_one = ? AND team_two =? AND start_time = ?;")) {
            preparedStatement.setLong(1, event.getTeamOneId());
            preparedStatement.setLong(2, event.getTeamTwoId());
            preparedStatement.setTimestamp(3, event.getStartTime());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result = new Event();
                    result.setTeamOneId(resultSet.getLong("team_one"));
                    result.setTeamTwoId(resultSet.getLong("team_two"));
                    result.setStartTime(resultSet.getTimestamp("start_time"));
                    result.setEndTime(resultSet.getTimestamp("end_time"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<EventView> getAllNotFinisedEvents() {
        List<EventView> result = new ArrayList<>();
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(
                     "SELECT e.id as id, t.name as team_one, t2.name as team_two, e.start_time, e.end_time, fe1.name as factor1,fe1.value as factor1_val, fe2.name as factor2, fe2.value as factor2_val, fe3.name as factor3,fe3.value as factor3_val FROM factor_event fe1\n" +
                             "LEFT JOIN event e on fe1.event_id = e.id\n" +
                             "LEFT JOIN team t on e.team_one = t.id\n" +
                             "LEFT JOIN team t2 on e.team_two = t2.id\n" +
                             "LEFT JOIN factor_event fe2 on e.id = fe1.event_id\n" +
                             "LEFT JOIN factor_event fe3 on e.id = fe2.event_id\n" +
                             "WHERE e.result IS NULL AND (fe1.name != fe2.name) AND (fe1.name!= fe3.name) AND (fe2.name != fe3.name) group by e.id;;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                EventView view;
                List<Factor> factors;
                while (resultSet.next()) {
                    view = new EventView();
                    factors = new ArrayList<>();
                    view.setId(resultSet.getLong("id"));
                    String teamTwo = resultSet.getString("team_two");
                    String teamOne = resultSet.getString("team_one");
                    view.setName(String.format("%s - %s", teamOne, teamTwo));
                    view.setStartTime(resultSet.getTimestamp("start_time"));
                    view.setEndTime(resultSet.getTimestamp("end_time"));
                    String factor1 = resultSet.getString("factor1");
                    double factor1Val = resultSet.getDouble("factor1_val");
                    factors.add(new Factor(FactorName.valueOf(factor1), factor1Val));
                    String factor2 = resultSet.getString("factor2");
                    double factor2Val = resultSet.getDouble("factor2_val");
                    factors.add(new Factor(FactorName.valueOf(factor2), factor2Val));
                    String factor3 = resultSet.getString("factor3");
                    double factor3Val = resultSet.getDouble("factor3_val");
                    factors.add(new Factor(FactorName.valueOf(factor3), factor3Val));
                    view.setFactors(factors);
                    result.add(view);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public EventView getSavedEventById(Long id) {
        EventView result = null;
        try {
            try (Connection connect = CONNECTION.connect();
                 PreparedStatement preparedStatement = connect.prepareStatement(
                         "SELECT e.id as id, t.name as team_one, t2.name as team_two, e.start_time, e.end_time, fe.id as factor_id, fe.name as factor_name, value as factor_value  FROM factor_event fe\n" +
                         "LEFT JOIN event e on fe.event_id = e.id\n" +
                         "LEFT JOIN team t on e.team_one = t.id\n" +
                         "LEFT JOIN team t2 on e.team_two = t2.id\n" +
                         "WHERE e.id = ?;")) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    result = new EventView();
                    List<Factor> factors = new ArrayList<>();
                    while (resultSet.next()) {
                        result.setId(resultSet.getLong("id"));
                        String teamOne = resultSet.getString("team_one");
                        String teamTwo = resultSet.getString("team_two");
                        result.setName(String.format("%s - %s", teamOne, teamTwo));
                        result.setStartTime(resultSet.getTimestamp("start_time"));
                        result.setEndTime(resultSet.getTimestamp("end_time"));
                        long factor_id = resultSet.getLong("factor_id");
                        String factorName = resultSet.getString("factor_name");
                        double factorValue = resultSet.getDouble("factor_value");
                        factors.add(new Factor(factor_id,FactorName.valueOf(factorName), factorValue));
                    }
                    result.setFactors(factors);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateEventInfo(Event event) {

    }

    @Override
    public void deleteEvent(Event event) {

    }

    @Override
    public List<League> getAllLeagues() {
        List<League> leagues = new ArrayList<>();
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM league;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    leagues.add(new League(id, name));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leagues;
    }

    @Override
    public List<Team> getAllTeamsByLeague(Long idLeague) {
        List<Team> teams = new ArrayList<>();
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM team WHERE team.id_league =?;")) {
            preparedStatement.setLong(1, idLeague);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    teams.add(new Team(id, name));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }
}