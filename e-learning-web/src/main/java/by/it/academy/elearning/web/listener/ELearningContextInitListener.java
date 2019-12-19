package by.it.academy.elearning.web.listener;

import by.it.academy.elearning.db.connection.pool.ElDataSource;
import by.it.academy.elearning.db.migration.DbMigration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@WebListener()
public class ELearningContextInitListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ELearningContextInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Context initialized");
        try {
            URL resource = this.getClass().getClassLoader().getResource("hikari.properties");
            ElDataSource.init(new File(resource.getFile()));
            DataSource dataSource = ElDataSource.getDataSource();
            DbMigration.migrate(dataSource);
        } catch (IOException e) {
            logger.error("error", e);
            throw new RuntimeException("Datasource initialisation error");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Context destroyed");
    }
}
