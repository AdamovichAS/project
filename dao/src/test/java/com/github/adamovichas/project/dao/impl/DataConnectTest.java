package com.github.adamovichas.project.dao.impl;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataConnectTest {

    private DataConnect dataConnect = DataConnect.CONNECT;

    @Test
    void Connection(){
        Connection connect = dataConnect.connect();
        assertNotNull(connect);
    }
}
