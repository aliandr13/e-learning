package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.RoleDao;
import by.it.academy.elearning.model.Role;
import by.it.academy.elearning.model.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Slf4j
@Repository
public class DoleDaoImpl extends AbstractDao<Role> implements RoleDao {

    public static final String FIND_BY_ROLE_NAME = "FROM Role where role =: roleName";

    protected DoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role getByName(RoleEnum role) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Role> query = entityManager.createQuery(FIND_BY_ROLE_NAME, entityClass);
        query.setParameter("roleName", role);
        return query.getSingleResult();

    }
}
