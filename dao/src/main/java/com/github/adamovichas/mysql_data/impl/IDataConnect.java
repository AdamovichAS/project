package com.github.adamovichas.mysql_data.impl;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDataConnect {
    Connection connect() throws SQLException;
}
