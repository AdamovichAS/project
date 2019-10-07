package by.itacademy.jd2.mysql_data;

import by.itacademy.jd2.dto.League;
import by.itacademy.jd2.dto.Team;
import by.itacademy.jd2.event.Event;
import by.itacademy.jd2.event.Factor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DataEvent implements IDataEvent {
    DATA_EVENT;

    private IDataConnect CONNECTION;

    DataEvent() {
        CONNECTION = DataConnect.CONNECT;
    }

    @Override
    public Event addEvent(Event event) {
        Connection connection = null;
        try {
            connection = CONNECTION.connect();
            connection.setAutoCommit(false);
            try(PreparedStatement statementEvent = connection.prepareStatement("INSERT INTO data.event(team_one, team_two, start_time, end_time) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement statementFactor = connection.prepareStatement("INSERT INTO data.factor_event(name, value, event_id) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)){
                statementEvent.setLong(1,event.getTeamOneId());
                statementEvent.setLong(2,event.getTeamTwoId());
                statementEvent.setTimestamp(3,event.getStartTime());
                statementEvent.setTimestamp(4,event.getEndTime());
                statementEvent.executeUpdate();
                final ResultSet generatedKeys = statementEvent.getGeneratedKeys();
                generatedKeys.next();
                final long idEvent = generatedKeys.getLong(1);
                List<Factor> factors = event.getFactors();
                for (Factor factor : factors) {
                    statementFactor.setString(1,factor.getName());
                    statementFactor.setDouble(2,factor.getValue());
                    statementFactor.setLong(3, idEvent);
                    statementFactor.addBatch();
                }
                statementFactor.executeBatch();
                final ResultSet factorKeys = statementFactor.getGeneratedKeys();
                List<Factor>resultFactors = new ArrayList<>();
                for (Factor factor : factors) {
                    factorKeys.next();
                    final long id = factorKeys.getLong(1);
                    resultFactors.add(new Factor(id, factor.getName(), factor.getValue(), idEvent));
                }
                Event resultEvent = new Event(event.getTeamOneId(),event.getTeamTwoId(),event.getStartTime(),event.getEndTime());
                resultEvent.setId(idEvent);
                resultEvent.setFactors(resultFactors);
                connection.commit();
                return resultEvent;
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
            preparedStatement.setLong(1,event.getTeamOneId());
            preparedStatement.setLong(2,event.getTeamTwoId());
            preparedStatement.setTimestamp(3,event.getStartTime());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
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