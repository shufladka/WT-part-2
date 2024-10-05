package by.bsuir.connection;

import by.bsuir.exceptions.ConnectionException;
import org.postgresql.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static ProxyConnection createConnection(Properties properties) throws ConnectionException {
        try {
            String url = properties.getProperty("URL");
            String username = properties.getProperty("USERNAME");
            String password = properties.getProperty("PASSWORD");
            return new ProxyConnection(DriverManager.getConnection(url, username, password));
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }
}