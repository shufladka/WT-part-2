package by.bsuir.connection;

import by.bsuir.dao.DaoFactory;
import by.bsuir.exceptions.ConnectionException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool INSTANCE;
    private static final String DB_PROPERTIES = "db.properties";
    private static final String POOL_SIZE = "POOL_SIZE";
    private BlockingQueue<ProxyConnection> availableConnectionsList;
    private BlockingQueue<ProxyConnection> usedConnectionsList;

    public static ConnectionPool getInstance()
    {
        if (INSTANCE == null) {
            synchronized (DaoFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                }
            }
        }

        return INSTANCE;
    }

    private ConnectionPool() {}

    public void initialize() throws ConnectionException {
        try {
            Properties properties = new Properties();
            properties.load(ConnectionFactory.class.getClassLoader().getResourceAsStream(DB_PROPERTIES));

            int poolSize = Integer.parseInt(properties.getProperty(POOL_SIZE));

            availableConnectionsList = new ArrayBlockingQueue<>(poolSize);
            usedConnectionsList = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                ProxyConnection connection = ConnectionFactory.createConnection(properties);
                availableConnectionsList.add(connection);
            }

        } catch (IOException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    public void releaseConnection(ProxyConnection proxy) throws ConnectionException {
        if (proxy != null) {
            try {
                usedConnectionsList.remove(proxy);
                availableConnectionsList.put(proxy);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ConnectionException(e.getMessage(), e);
            }
        }
    }

    public ProxyConnection getConnection() throws ConnectionException {
        ProxyConnection connection;
        try {
            connection = availableConnectionsList.take();
            usedConnectionsList.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionException(e.getMessage(), e);
        }
        return connection;
    }

    public void destroy() throws ConnectionException {
        try {
            for (ProxyConnection connection : availableConnectionsList) {
                connection.closeConnection();
            }
            for (ProxyConnection connection : usedConnectionsList) {
                connection.closeConnection();
            }
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }

    }
}
