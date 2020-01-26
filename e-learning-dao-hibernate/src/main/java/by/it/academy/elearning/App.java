package by.it.academy.elearning;

import by.it.academy.elearning.model.*;
import by.it.academy.elearning.hibernate.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

/**
 * Hello world!
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        insert(HibernateUtil.getSessionFactory().openSession());

        read(HibernateUtil.getSessionFactory().openSession());

        HibernateUtil.shutdown();
    }

    private static void read(Session session) {
        try {
//            Transaction transaction = session.beginTransaction();


            User user = session.get(User.class, 1L);
            log.info("user {}", user);

//            transaction.commit();
        } catch (Throwable e) {
            log.error("Some error", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    private static void insert(Session session) {
        try {
            Transaction transaction = session.beginTransaction();

            session.save(new Course("Java core"));
            Course java_ee = new Course("Java EE");
            session.save(java_ee);
            session.save(new Course("IOS"));
            session.save(new Course("Python"));

            Group group = new Group("JD2-64-19", LocalDate.parse("2019-11-24"), java_ee);
            session.save(group);


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

              transaction.commit();
        } catch (Throwable e) {
            log.error("Some error", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
