package by.it.academy.elearning;


import by.it.academy.elearning.entity.Employee;
import by.it.academy.elearning.entity.Meeting;
import by.it.academy.elearning.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Hello world!
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee employee = new Employee(null, "Ivan", "Ivanov", LocalDateTime.now(),
                null, null, new ArrayList<>());
        Meeting meeting = new Meeting("Hibernate relation grooming");
        meeting.getEmployees().add(employee);
        session.save(meeting);
        employee.getMeetings().add(meeting);
        session.saveOrUpdate(employee);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();
    }
}
