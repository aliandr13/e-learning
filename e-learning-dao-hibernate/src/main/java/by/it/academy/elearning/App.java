package by.it.academy.elearning;

import by.it.academy.elearning.entity.Course;
import by.it.academy.elearning.entity.Group;
import by.it.academy.elearning.entity.Student;
import by.it.academy.elearning.entity.Teacher;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();

            session.save(new Course("Java core"));
            Course java_ee = new Course("Java EE");
            session.save(java_ee);
            session.save(new Course("IOS"));
            session.save(new Course("Python"));

            Group group = new Group("JD2-64-19", LocalDate.parse("2019-11-24"), java_ee);
            session.save(group);


            Student student = Student.builder()
                    .grade(5.0f)
                    .homeWork("hw")
                    .firstName("IVAN")
                    .lastName("LASTBANE")
                    .phone("1234567")
                    .email("mail@gmail.com")
                    .build();
            session.save(student);
            Teacher teacher = Teacher.builder()
                    .work("hw")
                    .firstName("IVAN")
                    .lastName("LASTBANE")
                    .phone("1234567")
                    .email("mail@gmail.com")
                    .build();
            session.save(teacher);

//            Teacher teacher = new Teacher("some work");
//            session.save(teacher);


            transaction.commit();
        } catch (Throwable e) {
            log.error("Some error", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        HibernateUtil.shutdown();
    }
}
