package by.it.academy.elearning;

import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.*;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class App {


    public static void main(String[] args) {
        HibernateUtil.initSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();

        Role role = new Role();
        role.setRole(RoleEnum.ADMIN);

        session.saveOrUpdate(role);


        UserAuth auth = UserAuth.builder().login("dssd").password("pass").salt("sss").build();
        session.save(auth);

        User user = User.builder().role(role).email("email").name("sdf").surname("sdf").userAuth(auth).
                groups(new ArrayList<>())
                .build();

        session.save(user);

        Course course = new Course();
        course.setName("JAVA EE");

        session.save(course);


        Group group = new Group();

        group.setName("JD2");
        group.setCourse(course);
        group.setStartDate(LocalDate.of(2020, 1, 1));
        group.setStatus(GroupStatus.ACTIVE);
        group.getUsers().add(user);

        session.save(group);

        user.setGroups(Collections.singletonList(group));
        session.update(user);

        session.close();
        HibernateUtil.shutdown();
    }
}
