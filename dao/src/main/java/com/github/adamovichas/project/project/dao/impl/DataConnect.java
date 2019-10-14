package com.github.adamovichas.project.project.dao.impl;

import com.github.adamovichas.project.project.dao.IDataConnect;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public enum DataConnect implements IDataConnect {
    CONNECT;

    private static final Logger log = LoggerFactory.getLogger(DataConnect.class);
    private final ComboPooledDataSource pool;

    DataConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
         //   log.error("Driver connect exception");
            throw new RuntimeException(e);
        }
        pool = new ComboPooledDataSource();
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        pool.setJdbcUrl(url);
        pool.setUser(user);
        pool.setPassword(pass);

        pool.setMinPoolSize(5);
        pool.setAcquireIncrement(5);
        pool.setMaxPoolSize(10);
        pool.setMaxStatements(180);
    }

    public Connection connect()  {
        try {
            return this.pool.getConnection();
        } catch (SQLException e) {
            log.error("Sql connect exception");
            throw new RuntimeException(e);
        }
    }
}
