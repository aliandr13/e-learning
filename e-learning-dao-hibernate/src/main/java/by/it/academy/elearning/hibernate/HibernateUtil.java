package by.it.academy.elearning.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static synchronized void initSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable t) {
                System.err.println("Failed to create sessionFactory object." + t);
                throw new ExceptionInInitializerError(t);
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
