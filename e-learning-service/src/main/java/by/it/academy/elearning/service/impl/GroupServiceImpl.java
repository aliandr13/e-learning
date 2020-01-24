package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Setter
@Slf4j
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    @Override
    public List<Group> getAll() {
        try {
            return groupDao.getAll();
        } catch (SQLException e) {
            throw new ELearningException("Error while getting all groups", e);
        }
    }

    @Override
    public Group create(Group group) {
        try {
            log.info("Creating group {}", groupDao);
            Long id = groupDao.create(group);
            group.setId(id);
            return group;
        } catch (SQLException e) {
            log.error("Error while creating group", e);
            throw new ELearningException("Error while creating group", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            log.info("Deleting group by id {}", id);
            groupDao.delete(id);
        } catch (SQLException e) {
            throw new ELearningException("Error while deleting group", e);
        }
    }
}
