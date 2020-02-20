package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.model.Group;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDaoImpl extends AbstractDao<Group> implements GroupDao {

    protected GroupDaoImpl() {
        super(Group.class);
    }
}
