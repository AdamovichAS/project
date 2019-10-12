package com.github.adamovichas.mysql_data.impl;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataConnectTest {

    @Test
    void Connection(){
        DataConnect dataConnect = DataConnect.CONNECT;
        Connection connect = dataConnect.connect();
        assertNotNull(connect);
    }
}
