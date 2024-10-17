package by.bsuir.connection;

import by.bsuir.exceptions.ConnectionException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    static ProxyConnection createConnection(Properties properties) throws ConnectionException {
        try {
            String url = properties.getProperty("URL");
            String username = properties.getProperty("USERNAME");
            String password = properties.getProperty("PASSWORD");
            Class.forName(properties.getProperty("DRIVER"));
            return new ProxyConnection(DriverManager.getConnection(url, username, password));
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }
}