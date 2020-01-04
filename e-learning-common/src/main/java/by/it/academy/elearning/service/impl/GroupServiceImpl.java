package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Setter
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
}
