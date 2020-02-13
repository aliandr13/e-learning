package by.it.academy.elearning;

import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {


    public static void main(String[] args) {
        HibernateUtil.initSessionFactory();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.find(User.class, 4L);

        Group group = session.find(Group.class, 1L);

        group.getUsers().add(user);
        user.getGroups().add(group);

        session.saveOrUpdate(user);

        transaction.commit();


        session.close();
        HibernateUtil.shutdown();
    }
}
