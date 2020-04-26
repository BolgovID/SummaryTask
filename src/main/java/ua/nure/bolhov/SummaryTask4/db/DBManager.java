package ua.nure.bolhov.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.function.Supplier;

public class DBManager {


    private static DBManager instance;
    private DataSource dataSources;
    private final Logger LOGGER = Logger.getLogger(DBManager.class);

    public DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext;
            envContext = (Context) initContext.lookup("java:/comp/env");
            dataSources = (DataSource) envContext.lookup("jdbc/rent");
            LOGGER.info(LoggerUtils.DATA_SOURCE + dataSources);
        } catch (NamingException exc) {
            LOGGER.error(LoggerUtils.ERR_CANNOT_OBTAIN_DATA_SOURCE, exc);
        }
    }

    public static synchronized DBManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection(){
        try {
            return dataSources.getConnection();
        } catch (SQLException exc) {
            LOGGER.error(LoggerUtils.ERR_CANNOT_OBTAIN_CONNECTION, exc);
            return null;
        }
    }

    public <T> T doTransaction(Supplier<T> function) {
        T result = null;
        Connection connection = getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            result = function.get();
            connection.commit();
        } catch (SQLException exc) {
            rollback(connection);
            LOGGER.error(LoggerUtils.ERR_FAIL_TRANSACTION, exc);
        } finally {
           close(connection);
        }
        return result;
    }

    public void rollback(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(LoggerUtils.ERR_ROLLBACK_TRANSACTION, ex);
            }
        }
    }

    public static void close(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, Statement statement) {
        if (Objects.nonNull(statement)) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(connection);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (Objects.nonNull(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(connection, statement);
    }

}
