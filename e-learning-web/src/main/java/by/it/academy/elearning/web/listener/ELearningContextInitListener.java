package by.it.academy.elearning.web.listener;

import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Role;
import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.model.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ELearningContextInitListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ELearningContextInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Context initialized");
        try {
//            ResourceBundle bundle = ResourceBundle.getBundle("mysql_hikari");
//            ElDataSource.configure(bundle);
//            DataSource dataSource = ElDataSource.getDataSource();
//            DbMigration.migrate(dataSource);
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Role sr = new Role("STUDENT");
            Role tr = new Role("TEACHER");
            Role ar = new Role("ADMIN");

            session.save(sr);
            session.save(tr);
            session.save(ar);

            User user = User.builder()
                    .login("admin")
                    .password("5c5212a214c634002e7f970fa293746fb7d49c7cfc7087edecd4c32402c06007")
                    .salt("SEZhwD0GPt9W099OZkx2y9kZzJc=")
                    .role(sr).build();
            session.save(user);

            Student student = Student.builder()
                    .grade(5.0f)
                    .firstName("IVAN")
                    .lastName("LASTBANE")
                    .phone("1234567")
                    .email("mail@gmail.com")
                    .build();
            student.setUser(user);
            session.save(student);
            session.getTransaction().commit();
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
