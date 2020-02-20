package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.RoleEnum;
import by.it.academy.elearning.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public static final String SELECT_USER_BY_LOGIN = "SELECT e FROM User e join e.userAuth as a where a.login =: login";
    public static final String SELECT_USER_BY_ROLE_AND_GROUP_ID = "select u from User u join u.groups g where u.role.role =: roleName and g.id =: groupId";

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> getUserAuthByLogin(String login) {
        TypedQuery<User> query = getEntityManager().createQuery(SELECT_USER_BY_LOGIN, User.class);
        query.setParameter("login", login);
        User user = query.getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findByRoleAndGroup(RoleEnum roleName, long groupId) {
        TypedQuery<User> query = getEntityManager().createQuery(SELECT_USER_BY_ROLE_AND_GROUP_ID, User.class);
        query.setParameter("roleName", roleName);
        query.setParameter("groupId", groupId);
        return query.getResultList();
    }


    public User create(User user) {
        EntityManager entityManager = getEntityManager();
        List<Group> groups = user.getGroups();
        if (groups != null && !groups.isEmpty()) {
            for (Group group : groups) {
                entityManager.refresh(group);
                group.getUsers().add(user);
            }
        }
        entityManager.persist(user);
        return user;
    }
}
