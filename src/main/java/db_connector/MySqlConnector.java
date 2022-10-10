package db_connector;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://127.0.0.1:3307/";

    @Getter
    private final Connection connection;

    private MySqlConnector() throws SQLException {
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    private MySqlConnector(String databaseName) throws SQLException {
        connection = DriverManager.getConnection(URL + databaseName, USER_NAME, PASSWORD);
    }

    public static MySqlConnector connectToMySql() throws SQLException {
        return new MySqlConnector();
    }

    public static MySqlConnector useDatabase(String databaseName) throws SQLException {
        return new MySqlConnector(databaseName);
    }

}
