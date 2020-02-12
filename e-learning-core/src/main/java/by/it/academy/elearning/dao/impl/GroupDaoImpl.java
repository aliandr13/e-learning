package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.model.Group;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GroupDaoImpl extends AbstractDao<Group> implements GroupDao {

    protected GroupDaoImpl() {
        super(log, Group.class);
    }
}
