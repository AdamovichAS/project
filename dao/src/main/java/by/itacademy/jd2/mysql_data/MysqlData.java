package by.itacademy.jd2.mysql_data;

import by.itacademy.jd2.user.Role;
import by.itacademy.jd2.user.User;

import java.sql.*;
import java.util.*;

public enum  MysqlData implements IMysqlData {
    DATA;

    public Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String url = resourceBundle.getString("url");
        String user = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public String loginIsExist(String login) {
        String loginData = null;
        try (Connection connect = DATA.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT login FROM user WHERE login = ?;")){
            preparedStatement.setString(1,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    loginData = resultSet.getString("login");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginData;
    }

    @Override
    public List<String> userIsExist(String login, String password) {
        List<String>result = new ArrayList<>();
        try (Connection connect = DATA.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT login,password FROM user WHERE login = ? AND password = ?;")){
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    result.add(resultSet.getString("login"));
                    result.add(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;
        try (Connection connect = DATA.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO data.user (password,first_name, last_name, phone, email,age,country,login) VALUES (?,?,?,?,?,?,?,?);")){
            setNotNullUserColumns(user, preparedStatement);
            result = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void setNotNullUserColumns(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getPassword());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getPhone());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setInt(6, user.getAge());
        preparedStatement.setString(7, user.getCountry());
        preparedStatement.setString(8, user.getLogin());
    }

    @Override
    public HashMap<String, String> getUserFieldsByLogin(String login) {
        HashMap<String,String> userData = new HashMap<>();
        try (Connection connect = DATA.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM user WHERE login = ?;")){
            preparedStatement.setString(1,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    userData.put("login",resultSet.getString("login"));
                    userData.put("password",resultSet.getString("password"));
                    userData.put("firstName",resultSet.getString("first_name"));
                    userData.put("lastName",resultSet.getString("last_name"));
                    userData.put("phone",resultSet.getString("phone"));
                    userData.put("email",resultSet.getString("email"));
                    userData.put("age",String.valueOf(resultSet.getInt("age")));
                    userData.put("money",String.valueOf(resultSet.getInt("money")));
                    userData.put("country",resultSet.getString("country"));
                    userData.put("role",resultSet.getString("role"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userData;
    }

    @Override
    public boolean updateUserInfo(User user) {
        boolean result = false;
        try (Connection connect = DATA.connect();
             PreparedStatement preparedStatement = connect.prepareStatement("UPDATE user SET password = ?, first_name = ?, last_name =?, phone = ?, email = ?, age =?, country =? WHERE login = ?;")){
            setNotNullUserColumns(user, preparedStatement);
            result = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
