package by.it.academy.elearning;

import by.it.academy.elearning.entity.PersonEntity;
import by.it.academy.elearning.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        PersonEntity personEntity = new PersonEntity(
                null,
                "email@gamil.com",
                "demo",
                "user"
        );

        session.save(personEntity);

        personEntity.setFirstName("new name");
        session.getTransaction().commit();

        HibernateUtil.shutdown();

    }
}
