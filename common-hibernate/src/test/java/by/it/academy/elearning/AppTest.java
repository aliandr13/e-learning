package by.it.academy.elearning;

import by.it.academy.elearning.entity.Department;
import by.it.academy.elearning.entity.Employee;
import by.it.academy.elearning.entity.Hero;
import by.it.academy.elearning.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void getAllEmployee() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();

        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteria.from(Employee.class);
        criteria.select(employeeRoot);
        List<Employee> employees = sessionFactory.openSession().
                createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void getAllEmployeeOrderBy() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();

        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        criteria.select(cb.count(criteria.from(Employee.class)));
        long count = session.createQuery(criteria).getSingleResult();
        System.out.println(count);
    }

    @Test
    public void join() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();

        CriteriaQuery<Department> criteria = cb.createQuery(Department.class);
        Root<Department> department = criteria.from(Department.class);
        Join<Department, Employee> employeeJoin =
                department.join("employees", JoinType.INNER);
        criteria.where(cb.equal(employeeJoin.get("name"), "Yuli"));
        List<Department> departments = session.createQuery(criteria).getResultList();
        System.out.println(departments);
    }

    @Test
    public void join2() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();

        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        employee.fetch("department");
        ParameterExpression<Integer> ageParameter = cb.parameter(Integer.class);
        criteria.where(cb.gt(employee.get("age"), ageParameter));
        List<Employee> employees = session.createQuery(criteria)
                .setParameter(ageParameter, 42)
                .getResultList();

        List<Employee> departments = session.createQuery(criteria).getResultList();
        System.out.println(departments);
    }

    @Test
    public void pagination() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();

        int pageNumber = 1;
        int pageSize = 2;
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        criteria.from(Employee.class);
        TypedQuery<Employee> typedQuery = session.createQuery(criteria);
        typedQuery.setFirstResult(pageSize * (pageNumber - 1));
        typedQuery.setMaxResults(pageSize);
        List<Employee> employees = typedQuery.getResultList();

        List<Employee> departments = session.createQuery(criteria).getResultList();
        System.out.println(departments);
    }

    @Test
    public void detached() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        DetachedCriteria criteria = DetachedCriteria.forClass(Hero.class);

        DetachedCriteria departament = criteria.createCriteria("power");
        departament.add(Restrictions.eq("name", "Armageddon"));
        departament.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List list = departament.getExecutableCriteria(session).list();
        log.info(list.toString());
    }

    @Test
    public void getAllEmployee2() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();

        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        criteria.from(Employee.class);
        List<Employee> employees = sessionFactory.openSession().createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void getEmployeeByNameTest() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp)
                .where(cb.equal(emp.get("firstName"), "IVAN"));
        List<Employee> employees = session.createQuery(criteria).getResultList();
        log.info("result: ");
        log.info(employees.toString());

        session.close();
        HibernateUtil.shutdown();
    }

}
