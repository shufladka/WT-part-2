package by.bsuir.connection;

import by.bsuir.exceptions.ConnectionException;
import io.github.cdimascio.dotenv.Dotenv;
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

    static ProxyConnection createConnection(Properties dbProperties) throws ConnectionException {
        ProxyConnection proxy;

        try {
            Dotenv dotenv = Dotenv.load();

            String url = dbProperties.getProperty(dotenv.get("DB_URL"));
            String user = dbProperties.getProperty(dotenv.get("DB_USER"));
            String password = dbProperties.getProperty(dotenv.get("DB_PASSWORD"));
            proxy = new ProxyConnection(DriverManager.getConnection(url, user, password));
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }

        return proxy;
    }
}