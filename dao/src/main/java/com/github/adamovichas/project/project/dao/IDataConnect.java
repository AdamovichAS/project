package com.github.adamovichas.project.project.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDataConnect {
    Connection connect() throws SQLException;
}
