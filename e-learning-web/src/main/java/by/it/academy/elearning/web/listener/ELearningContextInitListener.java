package by.it.academy.elearning.web.listener;

import by.it.academy.elearning.db.connection.pool.ElDataSource;
import by.it.academy.elearning.db.migration.DbMigration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ResourceBundle;

@WebListener()
public class ELearningContextInitListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ELearningContextInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Context initialized");
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("mysql_hikari");
            ElDataSource.configure(bundle);
            DataSource dataSource = ElDataSource.getDataSource();
            DbMigration.migrate(dataSource);
        } catch (Throwable e) {
            logger.error("error", e);
            throw new RuntimeException("Datasource initialisation error", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Context destroyed");
    }
}
