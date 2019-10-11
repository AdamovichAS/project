package com.github.adamovichas.mysql_data;

import com.github.adamovichas.mysql_data.impl.IDataConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public enum DataConnect implements IDataConnect {
    CONNECT;

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
}
