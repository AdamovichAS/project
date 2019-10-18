package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.model.user.User;
import com.github.adamovichas.project.project.dao.IDataUser;
import com.github.adamovichas.project.project.dao.IDataConnect;
import com.github.adamovichas.project.model.user.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public enum DataUser implements IDataUser {
    DATA;

    private static final Logger log = LoggerFactory.getLogger(DataUser.class);
    private IDataConnect CONNECTION;

    DataUser() {
        CONNECTION = DataConnect.CONNECT;
    }

    @Override
    public String loginIsExist(String login) {
        String loginData = null;
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT login FROM user WHERE login = ?;")){
            preparedStatement.setString(1,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    loginData = resultSet.getString("login");
                }
                return loginData;
            }
        } catch (SQLException e) {
            log.error("Sql exception, Login {}",login);
        }
        throw new RuntimeException();
    }

    @Override
    public List<String> userIsExist(String login, String password) {
        List<String>result = new ArrayList<>();
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT login,password FROM user WHERE login = ? AND password = ?;")){
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    result.add(resultSet.getString("login"));
                    result.add(resultSet.getString("password"));
                }
            }
            return result;
        } catch (SQLException e) {
            log.error("Sql exception Login {} Password {}",login,password);
        }
        throw new RuntimeException();
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO data.user (password,first_name, last_name, phone, email,age,country,role,login) VALUES (?,?,?,?,?,?,?,?,?);")){
            setNotNullUserColumns(user, preparedStatement);
            result = preparedStatement.execute();
            return result;
        } catch (SQLException e) {
            log.error("Sql exception User {}",user);
        }
        throw new RuntimeException();
    }

    private void setNotNullUserColumns(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getPassword());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getPhone());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setInt(6, user.getAge());
        preparedStatement.setString(7, user.getCountry());
        preparedStatement.setString(8,user.getRole().toString());
        preparedStatement.setString(9, user.getLogin());
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM user WHERE login = ?;")){
            preparedStatement.setString(1,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAge(resultSet.getInt("age"));
                    user.setCountry(resultSet.getString("country"));
                    user.setRole(Role.valueOf(resultSet.getString("role")));
                }
                return user;
            } catch (SQLException e) {
                log.error("Execute exception Login {}",login);
            }
        } catch (SQLException e) {
            log.error("Sql exception login {}",login);
        }
        throw new RuntimeException();
    }

    @Override
    public boolean updateUserInfo(User user) {
        boolean result = false;
        try (Connection connect = CONNECTION.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("UPDATE user SET password = ?, first_name = ?, last_name =?, phone = ?, email = ?, age =?, country =?, role =? WHERE login = ?;")){
            setNotNullUserColumns(user, preparedStatement);
            result = preparedStatement.execute();
            return result;
        } catch (SQLException e) {
            log.error("Sql exception User {}",user);
        }
        throw new RuntimeException();
    }

}
