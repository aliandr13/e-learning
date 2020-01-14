package by.it.academy.elearning;

import by.it.academy.elearning.entity.Person;
import by.it.academy.elearning.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

public class PersonTest {

    @Test
    public void saveTest() {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        Person person = new Person(null, "Ivan", "Ivanov", 25);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();


            entityManager.getTransaction().begin();
            Person personFromDb = entityManager.find(Person.class, person.getId());
            entityManager.getTransaction().commit();
            Assert.assertEquals(person, personFromDb);
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @AfterClass
    public static void afterClass() throws Exception {
        HibernateUtil.close();
    }
}
