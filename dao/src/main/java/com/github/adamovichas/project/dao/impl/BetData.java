package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.model.dto.Bet;
import com.github.adamovichas.project.model.dto.BetView;
import com.github.adamovichas.project.model.event.FactorName;
import com.github.adamovichas.project.model.dto.Money;
import com.github.adamovichas.project.project.dao.IBetData;
import com.github.adamovichas.project.project.dao.IDataConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum BetData implements IBetData {
    BET_DATA;

    private static final Logger log = LoggerFactory.getLogger(BetData.class);
    private IDataConnect CONNECTION;

    BetData() {
        CONNECTION = DataConnect.CONNECT;
    }

    @Override
    public Money getMoneyByLogin(String userLogin) {
        Money money = null;
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT money.value as money FROM money WHERE user_login = ?")) {
            preparedStatement.setString(1, userLogin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    money = new Money(userLogin, resultSet.getInt("money"));
                }
            }
        } catch (SQLException e) {
            log.error("getMoneyByLogin Sql exception, Login {}",userLogin);
        }
        return money;
    }

    @Override
    public Long addBet(Bet bet) {
        Connection connection = null;
        Long idBet = null;
        try {
            connection = CONNECTION.connect();
            connection.setAutoCommit(false);
            try (PreparedStatement statementBet = connection.prepareStatement("INSERT INTO data.bet(user, factor_event_id, money_for_bet) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement statementMoney = connection.prepareStatement("UPDATE money set value = money.value - ? WHERE user_login = ?")) {

                statementBet.setString(1, bet.getUserLogin());
                statementBet.setLong(2, bet.getFactorId());
                statementBet.setInt(3, bet.getMoney());
                statementBet.executeUpdate();
                try(ResultSet generatedKeys = statementBet.getGeneratedKeys()) {
                    while (generatedKeys.next()) {
                        idBet = generatedKeys.getLong(1);
                    }
                    statementMoney.setInt(1, bet.getMoney());
                    statementMoney.setString(2, bet.getUserLogin());
                    statementMoney.executeUpdate();
                    connection.commit();
                    return idBet;
                }
            }
        } catch (Exception e) {
            log.error("AddBet exception, Bet {}",bet);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("AddBet rollback exception, Bet {}",bet);
                throw new RuntimeException(e);
            }

            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("AddBet connection.close exception, Bet {}",bet);
                }
            }
        }
    }

    @Override
    public BetView getViewById(Long idBet) {
        BetView betView = null;
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(
                     "SELECT bet.id as id,bet.user as login, t.name as team_one, t2.name as team_two, fe.name as factor, fe.value as factor_val, bet.money_for_bet FROM bet\n" +
                             "LEFT JOIN factor_event fe on bet.factor_event_id = fe.id\n" +
                             "LEFT JOIN event e on fe.event_id = e.id\n" +
                             "LEFT JOIN team t on e.team_one = t.id\n" +
                             "LEFT JOIN team t2 on e.team_two = t2.id\n" +
                             "WHERE bet.id = ?;")) {
            preparedStatement.setLong(1, idBet);
            preparedStatement.executeQuery();
            betView = new BetView();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    betView.setId(idBet);
                    String teamOne = resultSet.getString("team_one");
                    String teamTwo = resultSet.getString("team_two");
                    betView.setEvent(String.format("%s - %s", teamOne, teamTwo));
                    betView.setLogin(resultSet.getString("login"));
                    betView.setFactorName(FactorName.valueOf(resultSet.getString("factor")));
                    betView.setMoney(resultSet.getInt("money_for_bet"));
                    betView.setFactorValue(resultSet.getDouble("factor_val"));

                }
                return betView;
            }

        } catch (SQLException e) {
            log.error("GetViewById Sql exception, idBet {}",idBet);
        }
        throw new RuntimeException();
    }

    @Override
    public List<BetView> getNotFinishedBetByLogin(String login) {
        List<BetView> bets = new ArrayList<>();
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(
                     "SELECT bet.id as id,bet.user as login, t.name as team_one, t2.name as team_two, fe.name as factor, fe.value as factor_val, bet.money_for_bet as money FROM bet\n" +
                             "LEFT JOIN factor_event fe on bet.factor_event_id = fe.id\n" +
                             "LEFT JOIN event e on fe.event_id = e.id\n" +
                             "LEFT JOIN team t on e.team_one = t.id\n" +
                             "LEFT JOIN team t2 on e.team_two = t2.id\n" +
                             "WHERE bet.result is null and bet.user =?;")) {
            preparedStatement.setString(1, login);
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    BetView betView = new BetView();
                    betView.setId(resultSet.getLong("id"));
                    String teamOne = resultSet.getString("team_one");
                    String teamTwo = resultSet.getString("team_two");
                    betView.setEvent(String.format("%s - %s", teamOne, teamTwo));
                    betView.setLogin(login);
                    betView.setMoney(resultSet.getInt("money"));
                    betView.setFactorName(FactorName.valueOf(resultSet.getString("factor")));
                    betView.setFactorValue(resultSet.getDouble("factor_val"));
                    bets.add(betView);
                }
                return bets;

            }
        } catch (SQLException e) {
            log.error("getNotFinishedBetByLogin Sql exception, Login {}",login);
        }
        throw new RuntimeException();
    }

    @Override
    public void CancelBetById(Long idBet) {
        Connection connection = null;
        String login = null;
        Integer bet = null;
        try {
            connection = CONNECTION.connect();
            connection.setAutoCommit(false);
            try (PreparedStatement statementBet = connection.prepareStatement("SELECT bet.user as login, money_for_bet , money.value as deposit from bet LEFT JOIN money on money.user_login = bet.user where id = ?;");
                 PreparedStatement statementDeleteBet = connection.prepareStatement("DELETE FROM bet WHERE bet.id = ?;");
                 PreparedStatement statementMoney = connection.prepareStatement("UPDATE money set value = value + ? WHERE user_login = ?")){
                statementBet.setLong(1, idBet);
                statementBet.executeQuery();
                try(ResultSet resultSet = statementBet.getResultSet()) {
                    while (resultSet.next()) {
                        login = resultSet.getString("login");
                        bet = resultSet.getInt("money_for_bet");
                    }
                    statementMoney.setInt(1, bet);
                    statementMoney.setString(2, login);
                    statementMoney.executeUpdate();
                    statementDeleteBet.setLong(1, idBet);
                    statementDeleteBet.executeUpdate();
                    connection.commit();
                }
            }
        } catch (Exception e) {
            log.error("CancelBetById exception, idBet {}",idBet);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("CancelBetById rollback exception, idBet {}",idBet);
                throw new RuntimeException(e);
            }

            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("CancelBetById connection.close exception, idBet {}",idBet);
                }
            }
        }

    }


}

