package by.it.academy.elearning;

import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Role;
import by.it.academy.elearning.model.RoleEnum;
import by.it.academy.elearning.model.User;
import by.it.academy.elearning.model.UserAuth;
import org.hibernate.Session;

public class App {


    public static void main(String[] args) {
        HibernateUtil.initSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();

        Role role = new Role();
        role.setRole(RoleEnum.ADMIN);

        session.saveOrUpdate(role);


        UserAuth auth = UserAuth.builder().password("pass").salt("sss").build();
        session.save(auth);

        User user = User.builder().role(role).email("email").firstName("sdf").lastName("sdf").userAuth(auth).build();

        session.save(user);

        session.close();
        HibernateUtil.shutdown();
    }
}
