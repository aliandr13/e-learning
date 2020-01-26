package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.db.connection.pool.ElDataSource;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

abstract class AbstractDao {

    protected final Logger logger;

    protected AbstractDao(Logger logger) {
        this.logger = logger;
    }


    protected Connection getConnection() throws SQLException {
        return ElDataSource.getConnection();
    }

    protected void closeQuietly(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            logger.error("Error while closing closable: " + closeable, e);
        }
    }

}
